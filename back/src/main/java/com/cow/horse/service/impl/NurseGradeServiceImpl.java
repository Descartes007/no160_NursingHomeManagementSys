package com.cow.horse.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cow.horse.common.constant.ChargeEnum;
import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.common.util.AssertUtil;
import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.NurseGradeMapper;
import com.cow.horse.dao.mapper.NurseItemMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.po.NurseGrade;
import com.cow.horse.entity.po.ServiceItem;
import com.cow.horse.entity.query.PageNurseGradeByKeyQuery;
import com.cow.horse.entity.query.PageServiceByKeyQuery;
import com.cow.horse.entity.vo.PageNurseGradeByKeyVo;
import com.cow.horse.entity.vo.PageServiceByKeyVo;
import com.cow.horse.service.common.ElderFunc;
import com.cow.horse.service.common.NurseGradeFunc;
import com.cow.horse.service.common.NurseItemFunc;
import com.cow.horse.service.common.ServiceItemFunc;
import com.cow.horse.entity.query.OperateNurseGradeQuery;
import com.cow.horse.service.NurseGradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class NurseGradeServiceImpl implements NurseGradeService {
    @Resource
    private ServiceItemFunc serviceItemFunc;
    @Resource
    private NurseGradeMapper nurseGradeMapper;
    @Resource
    private NurseGradeFunc nurseGradeFunc;
    @Resource
    private NurseItemMapper nurseItemMapper;
    @Resource
    private NurseItemFunc nurseItemFunc;
    @Resource
    private ElderFunc elderFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageNurseGradeByKey(PageNurseGradeByKeyQuery query) {
        // 获取未被删除的护理等级
        List<NurseGrade> listNotDelNurseGrade = nurseGradeFunc.listNotDelNurseGrade(query.getGradeName(), query.getNurseType());
        // 转换实体
        List<PageNurseGradeByKeyVo> pageNurseGradeByKeyVoList = BeanUtil.copyToList(listNotDelNurseGrade, PageNurseGradeByKeyVo.class);
        // 封装返回数据
        PageResult<PageNurseGradeByKeyVo> pageResult = pageUtil.packPageResultData(pageNurseGradeByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result pageServiceByKey(PageServiceByKeyQuery query) {
        // 根据搜索关键字查询服务项目
        List<ServiceItem> listNotDelServiceItem = serviceItemFunc.listNotDelServiceItemByKey(query.getName(), query.getTypeId(), ChargeEnum.MONTH.getMethod());
        // 实体转换
        List<PageServiceByKeyVo> pageServiceByKeyVoList = BeanUtil.copyToList(listNotDelServiceItem, PageServiceByKeyVo.class);
        // 封装返回数据
        PageResult<PageServiceByKeyVo> pageResult = pageUtil.packPageResultData(pageServiceByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    @Transactional
    public Result addNurseGrade(OperateNurseGradeQuery query) {
        // 验证护理等级名称是否已存在
        AssertUtil.isNull(nurseGradeFunc.getNurseGradeByName(query.getName()), ExceptionEnum.NURSE_GRADE_REPEAT);
        // 初始化护理等级
        query.setId(null);
        NurseGrade nurseGrade = BeanUtil.toBean(query, NurseGrade.class);
        nurseGrade.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        nurseGradeMapper.insert(nurseGrade);
        // 批量插入护理等级服务
        nurseItemFunc.saveBatchActiveParticipant(query.getServiceIdList(), nurseGrade.getId(), false);
        return Result.success();
    }

    @Override
    public Result getNurseGradeById(Long nurseGradeId) {
        return Result.success(nurseGradeFunc.getNurseGradeById(nurseGradeId));
    }

    @Override
    @Transactional
    public Result editNurseGrade(OperateNurseGradeQuery query) {
        // 验证护理等级名称是否已存在
        NurseGrade nurseGradeByName = nurseGradeFunc.getNurseGradeByName(query.getName());
        boolean checkName = nurseGradeByName != null && !Objects.equals(nurseGradeByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.NURSE_GRADE_REPEAT);
        // 封装修改
        NurseGrade nurseGrade = BeanUtil.toBean(query, NurseGrade.class);
        // 修改
        nurseGradeMapper.updateById(nurseGrade);
        // 批量插入护理等级服务
        nurseItemFunc.saveBatchActiveParticipant(query.getServiceIdList(), nurseGrade.getId(), true);
        return Result.success();
    }

    @Override
    public Result deleteNurseGrade(Long nurseGradeId) {
        // 判断是否有入住老人选择该护理等级
        elderFunc.checkNurse(nurseGradeId);
        // 封装修改
        NurseGrade nurseGrade = new NurseGrade();
        nurseGrade.setId(nurseGradeId);
        nurseGrade.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        nurseGradeMapper.updateById(nurseGrade);
        return Result.success();
    }
}
