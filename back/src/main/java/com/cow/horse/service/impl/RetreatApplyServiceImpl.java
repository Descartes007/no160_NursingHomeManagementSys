package com.cow.horse.service.impl;

import com.cow.horse.common.constant.AuditEnum;
import com.cow.horse.common.constant.BedEnum;
import com.cow.horse.common.constant.CheckEnum;
import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.common.util.AssertUtil;
import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.BedMapper;
import com.cow.horse.dao.mapper.ElderMapper;
import com.cow.horse.dao.mapper.RetreatApplyMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.po.Bed;
import com.cow.horse.entity.po.Elder;
import com.cow.horse.entity.po.RetreatApply;
import com.cow.horse.entity.query.PageSearchElderByKeyQuery;
import com.cow.horse.entity.vo.PageRetreatByKeyVo;
import com.cow.horse.entity.vo.PageSearchElderByKeyVo;
import com.cow.horse.entity.query.PageRetreatApplyQuery;
import com.cow.horse.service.RetreatApplyService;
import com.cow.horse.service.common.CommonFunc;
import com.cow.horse.service.common.RetreatApplyFunc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class RetreatApplyServiceImpl implements RetreatApplyService {
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private BedMapper bedMapper;
    @Resource
    private RetreatApplyFunc retreatApplyFunc;
    @Resource
    private RetreatApplyMapper retreatApplyMapper;
    @Resource
    private CommonFunc commonFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageRetreatApplyByKey(PageRetreatApplyQuery query) {
        // 根据搜索关键字查询退住申请信息
        List<PageRetreatByKeyVo> pageRetreatByKeyVoList = retreatApplyMapper.listRetreatApplyByKey(query);
        // 封装返回数据
        PageResult<PageRetreatByKeyVo> pageResult = pageUtil.packPageResultData(pageRetreatByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result pageSearchElderByKey(PageSearchElderByKeyQuery query) {
        List<String> checkFlagList = new ArrayList<>(Collections.singletonList(CheckEnum.ENTER.getStatus()));
        // 根据姓名和联系电话获取入住和退住审核老人列表
        List<PageSearchElderByKeyVo> pageSearchElderByKeyVoList = commonFunc.listPageElderByKey(query, checkFlagList);
        // 获取未审核的退住申请
        List<RetreatApply> retreatApplyList = retreatApplyFunc.listNotAuditApply();
        // 根据退住申请列表过滤老人
        pageSearchElderByKeyVoList = pageSearchElderByKeyVoList.stream().filter(pageSearchElderByKeyVo -> {
            AtomicBoolean existFlag = new AtomicBoolean(false);
            retreatApplyList.forEach(outward -> {
                if (Objects.equals(outward.getElderId(), pageSearchElderByKeyVo.getId())) {
                    existFlag.set(true);
                }
            });
            return !existFlag.get();
        }).collect(Collectors.toList());
        // 封装返回数据
        PageResult<PageSearchElderByKeyVo> pageResult = pageUtil.packPageResultData(pageSearchElderByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    @Transactional
    public Result addRetreatApply(Long elderId) {
        // 根据编号获取老人
        Elder elder = elderMapper.selectById(elderId);
        // 验证老人是否存在
        AssertUtil.notNull(elder, ExceptionEnum.DATA_NOT_EXIST);
        // 验证老人是否为入住状态
        AssertUtil.isTrue(Objects.equals(elder.getCheckFlag(), CheckEnum.ENTER.getStatus()), ExceptionEnum.ELDER_NOT_ENTER);
        // 初始化退住申请
        RetreatApply retreatApply = new RetreatApply();
        retreatApply.setElderId(elderId);
        retreatApply.setApplyFlag(AuditEnum.STAY_AUDIT.getStatus());
        // 新增
        retreatApplyMapper.insert(retreatApply);
        // 封装老人修改
        elder.setCheckFlag(CheckEnum.EXIT_AUDIT.getStatus());
        // 修改
        elderMapper.updateById(elder);
        // 封装床位修改
        Bed bed = new Bed();
        bed.setId(elder.getBedId());
        bed.setBedFlag(BedEnum.EXIT_AUDIT.getStatus());
        // 修改
        bedMapper.updateById(bed);
        return Result.success();
    }
}
