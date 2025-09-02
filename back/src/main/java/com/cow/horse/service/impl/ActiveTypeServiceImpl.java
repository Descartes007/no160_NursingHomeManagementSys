package com.cow.horse.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.common.util.AssertUtil;
import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.ActiveTypeMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.po.ActiveType;
import com.cow.horse.entity.vo.OperateActiveTypeVo;
import com.cow.horse.entity.vo.PageActiveTypeByKeyVo;
import com.cow.horse.entity.query.OperateActiveTypeQuery;
import com.cow.horse.entity.query.PageActiveTypeByKeyQuery;
import com.cow.horse.service.ActiveTypeService;
import com.cow.horse.service.common.ActiveTypeFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class ActiveTypeServiceImpl implements ActiveTypeService {
    @Resource
    private ActiveTypeMapper activeTypeMapper;
    @Resource
    private ActiveTypeFunc activeTypeFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageActiveTypeByKey(PageActiveTypeByKeyQuery query) {
        // 根据关键字获取所有未被删除的活动分类
        List<ActiveType> activeTypeList = activeTypeFunc.listNotDelActiveType(query.getActiveTypeName());
        // 列表实体转换
        List<PageActiveTypeByKeyVo> pageActiveTypeByKeyVoList = BeanUtil.copyToList(activeTypeList, PageActiveTypeByKeyVo.class);
        // 封装返回数据
        PageResult<PageActiveTypeByKeyVo> pageResult = pageUtil.packPageResultData(pageActiveTypeByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addActiveType(String activeTypeName) {
        // 判断活动分类是否已存在
        AssertUtil.isNull(activeTypeFunc.getActiveTypeByName(activeTypeName), ExceptionEnum.ACTIVE_TYPE_REPEAT);
        // 初始化活动分类
        ActiveType activeType = new ActiveType();
        activeType.setName(activeTypeName);
        activeType.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        activeTypeMapper.insert(activeType);
        return Result.success();
    }

    @Override
    public Result getActiveTypeById(Long activeTypeId) {
        // 根据编号获取活动分类
        ActiveType activeType = activeTypeMapper.selectById(activeTypeId);
        // 判断是否为空
        AssertUtil.notNull(activeType, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(activeType, OperateActiveTypeVo.class));
    }

    @Override
    public Result editActiveType(OperateActiveTypeQuery query) {
        // 判断活动分类是否已存在
        ActiveType activeTypeByName = activeTypeFunc.getActiveTypeByName(query.getName());
        boolean checkName = activeTypeByName != null && !Objects.equals(activeTypeByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.ACTIVE_TYPE_REPEAT);
        // 封装修改
        ActiveType activeType = new ActiveType();
        activeType.setId(query.getId());
        activeType.setName(query.getName());
        // 修改
        activeTypeMapper.updateById(activeType);
        return Result.success();
    }

    @Override
    public Result deleteActiveType(Long activeTypeId) {
        // 封装修改
        ActiveType activeType = new ActiveType();
        activeType.setId(activeTypeId);
        activeType.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        activeTypeMapper.updateById(activeType);
        return Result.success();
    }
}
