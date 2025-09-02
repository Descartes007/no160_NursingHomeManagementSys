package com.cow.horse.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.cow.horse.common.constant.CodeEnum;
import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.common.util.AssertUtil;
import com.cow.horse.common.util.ExcelUtil;
import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.BedMapper;
import com.cow.horse.dao.mapper.ElderMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.po.Elder;
import com.cow.horse.entity.vo.GetElderRecordByIdVo;
import com.cow.horse.entity.vo.PageElderByKeyVo;
import com.cow.horse.service.common.CateringSetFunc;
import com.cow.horse.service.common.EmergencyContactFunc;
import com.cow.horse.service.common.NurseGradeFunc;
import com.cow.horse.entity.query.PageElderByKeyQuery;
import com.cow.horse.service.ElderRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class ElderRecordServiceImpl implements ElderRecordService {
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private PageUtil pageUtil;
    @Resource
    private BedMapper bedMapper;
    @Resource
    private EmergencyContactFunc emergencyContactFunc;
    @Resource
    private NurseGradeFunc nurseGradeFunc;
    @Resource
    private CateringSetFunc cateringSetFunc;
    @Resource
    private ExcelUtil excelUtil;

    @Override
    public Result exportExcel() throws IOException {
        // 获取长者列表
        List<PageElderByKeyVo> pageElderByKeyVoList = elderMapper.listElderByKey(new PageElderByKeyQuery());
        // 导出excel并返回requestPath
        String requestPath = excelUtil.exportExcel(pageElderByKeyVoList, PageElderByKeyVo.class);
        return Result.success(CodeEnum.SUCCESS.getMsg(), requestPath);
    }

    @Override
    public Result pageElderByKey(PageElderByKeyQuery query) {
        // 根据关键字获取长者列表
        List<PageElderByKeyVo> pageElderByKeyVoList = elderMapper.listElderByKey(query);
        // 封装返回数据
        PageResult<PageElderByKeyVo> pageResult = pageUtil.packPageResultData(pageElderByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result getElderRecordById(Long elderId) {
        // 根据编号获取老人
        Elder elder = elderMapper.selectById(elderId);
        // 验证老人是否存在
        AssertUtil.notNull(elder, ExceptionEnum.DATA_NOT_EXIST);
        // 实体转换
        GetElderRecordByIdVo getElderRecordByIdVo = BeanUtil.toBean(elder, GetElderRecordByIdVo.class);
        // 根据老人编号获取紧急联系人
        getElderRecordByIdVo.setElderEmergencyContactByIdVoList(emergencyContactFunc.listEmgencyContactByElderId(elderId));
        // 根据编号获取护理等级
        if (ObjUtil.isNotEmpty(elder.getNursingGradeId())) {
            getElderRecordByIdVo.setElderNurseGradeByIdVo(nurseGradeFunc.getNurseGradeById(elder.getNursingGradeId()));
        }
        // 根据编号获取餐饮套餐
        if (ObjUtil.isNotEmpty(elder.getCateringSetId())) {
            getElderRecordByIdVo.setElderCateringSetByIdVo(cateringSetFunc.getCateringSetById(elder.getCateringSetId()));
        }
        // 根据编号获取床位
        if (ObjUtil.isNotEmpty(elder.getBedId())) {
            getElderRecordByIdVo.setElderBedByIdVo(bedMapper.getBedById(elder.getBedId()));
        }
        return Result.success(getElderRecordByIdVo);
    }
}
