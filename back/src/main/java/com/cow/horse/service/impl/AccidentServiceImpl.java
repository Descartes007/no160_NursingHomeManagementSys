package com.cow.horse.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.common.util.AssertUtil;
import com.cow.horse.common.util.DateUtilWen;
import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.AccidentMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.po.Accident;
import com.cow.horse.entity.query.AddAccidentQuery;
import com.cow.horse.entity.query.EditAccidentQuery;
import com.cow.horse.entity.query.PageAccidentByKeyQuery;
import com.cow.horse.entity.vo.GetAccidentByIdVo;
import com.cow.horse.entity.vo.PageAccidentByKeyVo;
import com.cow.horse.service.common.AccidentFunc;
import com.cow.horse.service.AccidentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccidentServiceImpl implements AccidentService {
    @Resource
    private AccidentMapper accidentMapper;
    @Resource
    private AccidentFunc accidentFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageAccidentByKey(PageAccidentByKeyQuery query) {
        // 根据关键字获取事故登记列表
        List<PageAccidentByKeyVo> pageAccidentByKeyVoList = accidentMapper.listAccidentByKeyVo(query);
        // 封装返回数据
        PageResult<PageAccidentByKeyVo> pageResult = pageUtil.packPageResultData(pageAccidentByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addAccident(AddAccidentQuery query) {
        // 初始化事故登记
        query.setId(null);
        Accident accident = BeanUtil.toBean(query, Accident.class);
        accident.setOccurDate(DateUtilWen.dateStrToDate(query.getOccurDate()));
        accident.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        accidentMapper.insert(accident);
        return Result.success();
    }

    @Override
    public Result getAccidentById(Long accidentId) {
        // 根据编号获取事故登记
        GetAccidentByIdVo accidentById = accidentMapper.getAccidentById(accidentId);
        // 验证事故登记是否存在
        AssertUtil.notNull(accidentById, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(accidentById);
    }

    @Override
    public Result editAccident(EditAccidentQuery query) {
        // 验证事故登记
        Accident accident = accidentFunc.checkAccident(query.getId(), false);
        // 封装修改
        BeanUtil.copyProperties(query, accident);
        accident.setOccurDate(DateUtilWen.dateStrToDate(query.getOccurDate()));
        // 修改
        accidentMapper.updateById(accident);
        return Result.success();
    }

    @Override
    public Result deleteAccident(Long accidentId) {
        // 验证事故登记
        Accident accident = accidentFunc.checkAccident(accidentId, true);
        // 封装修改
        accident.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        accidentMapper.updateById(accident);
        return Result.success();
    }
}
