package com.cow.horse.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.common.util.AssertUtil;
import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.CateringSetMapper;
import com.cow.horse.dao.mapper.SetDishesMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.po.CateringSet;
import com.cow.horse.entity.query.OperateCateringSetQuery;
import com.cow.horse.entity.query.PageCateringSetByKeyQuery;
import com.cow.horse.entity.vo.PageCateringSetByKeyVo;
import com.cow.horse.service.common.CateringSetFunc;
import com.cow.horse.service.common.ElderFunc;
import com.cow.horse.service.CateringSetService;
import com.cow.horse.service.common.SetDishesFunc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class CateringSetServiceImpl implements CateringSetService {
    @Resource
    private CateringSetMapper cateringSetMapper;
    @Resource
    private CateringSetFunc cateringSetFunc;
    @Resource
    private SetDishesMapper setDishesMapper;
    @Resource
    private SetDishesFunc setDishesFunc;
    @Resource
    private ElderFunc elderFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageCateringSetByKey(PageCateringSetByKeyQuery query) {
        // 获取未被删除的餐饮套餐
        List<CateringSet> listNotDelCateringSet = cateringSetFunc.listNotDelCateringSet(query.getSetName());
        // 实体转换
        List<PageCateringSetByKeyVo> pageCateringSetByKeyVoList = BeanUtil.copyToList(listNotDelCateringSet, PageCateringSetByKeyVo.class);
        // 封装返回数据
        PageResult<PageCateringSetByKeyVo> pageResult = pageUtil.packPageResultData(pageCateringSetByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    @Transactional
    public Result addCateringSet(OperateCateringSetQuery query) {
        // 验证套餐名称是否已存在
        AssertUtil.isNull(cateringSetFunc.getCateringSetByName(query.getName()), ExceptionEnum.SET_REPEAT);
        // 初始化餐饮套餐
        query.setId(null);
        CateringSet cateringSet = BeanUtil.toBean(query, CateringSet.class);
        cateringSet.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        cateringSetMapper.insert(cateringSet);
        // 批量插入套餐食物
        setDishesFunc.saveBatchSetDishes(query.getDishesIdList(), cateringSet.getId(), false);
        return Result.success();
    }

    @Override
    public Result getCateringSetById(Long setId) {
        return Result.success(cateringSetFunc.getCateringSetById(setId));
    }

    @Override
    public Result editCateringSet(OperateCateringSetQuery query) {
        // 验证套餐名称是否已存在
        CateringSet cateringSetByName = cateringSetFunc.getCateringSetByName(query.getName());
        boolean checkName = cateringSetByName != null && !Objects.equals(cateringSetByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.SET_REPEAT);
        // 封装修改
        CateringSet cateringSet = BeanUtil.toBean(query, CateringSet.class);
        // 修改
        cateringSetMapper.updateById(cateringSet);
        // 批量插入套餐食物
        setDishesFunc.saveBatchSetDishes(query.getDishesIdList(), cateringSet.getId(), true);
        return Result.success();
    }

    @Override
    public Result deleteCateringSet(Long setId) {
        // 判断是否有入住老人选择该餐饮套餐
        elderFunc.checkCatering(setId);
        // 封装修改
        CateringSet cateringSet = new CateringSet();
        cateringSet.setId(setId);
        cateringSet.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        cateringSetMapper.updateById(cateringSet);
        return Result.success();
    }
}
