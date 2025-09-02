package com.cow.horse.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.common.constant.VisitEnum;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.common.util.AssertUtil;
import com.cow.horse.common.util.DateUtilWen;
import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.VisitMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.po.Visit;
import com.cow.horse.entity.query.AddVisitQuery;
import com.cow.horse.entity.query.RecordLeaveQuery;
import com.cow.horse.entity.vo.GetVisitByIdVo;
import com.cow.horse.entity.vo.PageVisitByKeyVo;
import com.cow.horse.service.common.VisitFunc;
import com.cow.horse.entity.query.EditVisitQuery;
import com.cow.horse.entity.query.PageVisitByKeyQuery;
import com.cow.horse.service.VisitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {
    @Resource
    private VisitMapper visitMapper;
    @Resource
    private VisitFunc visitFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageVisitByKey(PageVisitByKeyQuery query) {
        // 根据关键字获取来访登记
        List<PageVisitByKeyVo> pageVisitByKeyVoList = visitMapper.listVisitByKey(query);
        // 封装返回数据
        PageResult<PageVisitByKeyVo> pageResult = pageUtil.packPageResultData(pageVisitByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addVisit(AddVisitQuery query) {
        // 初始化来访登记
        query.setId(null);
        Visit visit = BeanUtil.toBean(query, Visit.class);
        visit.setVisitDate(DateUtilWen.dateStrToDate(query.getVisitDate()));
        visit.setVisitFlag(VisitEnum.STAY_LEAVE.getStatus());
        visit.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        visitMapper.insert(visit);
        return Result.success();
    }

    @Override
    public Result getVisitById(Long visitId) {
        // 根据编号获取来访登记
        GetVisitByIdVo getVisitByIdVo = visitMapper.getVisitById(visitId);
        // 验证来访登记是否存在
        AssertUtil.notNull(getVisitByIdVo, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(getVisitByIdVo);
    }

    @Override
    public Result editVisit(EditVisitQuery query) {
        // 验证来访登记
        Visit visit = visitFunc.checkVisit(query.getId(), false);
        // 封装修改
        BeanUtil.copyProperties(query, visit);
        visit.setVisitDate(DateUtilWen.dateStrToDate(query.getVisitDate()));
        // 修改
        visitMapper.updateById(visit);
        return Result.success();
    }

    @Override
    public Result recordLeave(RecordLeaveQuery query) {
        // 验证来访登记
        Visit visit = visitFunc.checkVisit(query.getId(), false);
        // 封装修改
        visit.setLeaveDate(DateUtilWen.dateStrToDate(query.getLeaveDate()));
        visit.setVisitFlag(VisitEnum.ALREADY_LEAVE.getStatus());
        // 修改
        visitMapper.updateById(visit);
        return Result.success();
    }

    @Override
    public Result deleteVisit(Long visitId) {
        // 验证来访登记
        Visit visit = visitFunc.checkVisit(visitId, true);
        // 封装修改
        visit.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        visitMapper.updateById(visit);
        return Result.success();
    }
}
