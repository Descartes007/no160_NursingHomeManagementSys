package com.cow.horse.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.common.util.AssertUtil;
import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.SourceMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.po.Source;
import com.cow.horse.entity.vo.OperateSourceVo;
import com.cow.horse.entity.vo.PageSourceByKeyVo;
import com.cow.horse.service.common.SourceFunc;
import com.cow.horse.entity.query.OperateSourceQuery;
import com.cow.horse.entity.query.PageSourceByKeyQuery;
import com.cow.horse.service.SourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class SourceServiceImpl implements SourceService {
    @Resource
    private SourceMapper sourceMapper;
    @Resource
    private SourceFunc sourceFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageSourceByKey(PageSourceByKeyQuery query) {
        // 根据关键字获取所有未被删除的来源渠道
        List<Source> sourceList = sourceFunc.listNotDelSource(query.getSourceName());
        // 列表实体转换
        List<PageSourceByKeyVo> pageSourceByKeyVoList = BeanUtil.copyToList(sourceList, PageSourceByKeyVo.class);
        // 封装返回数据
        PageResult<PageSourceByKeyVo> pageResult = pageUtil.packPageResultData(pageSourceByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addSource(String sourceName) {
        // 判断来源渠道是否已存在
        AssertUtil.isNull(sourceFunc.getSourceByName(sourceName), ExceptionEnum.SOURCE_REPEAT);
        // 初始化来源渠道
        Source source = new Source();
        source.setName(sourceName);
        source.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        sourceMapper.insert(source);
        return Result.success();
    }

    @Override
    public Result getSourceById(Long sourceId) {
        // 根据编号获取渠道来源
        Source source = sourceMapper.selectById(sourceId);
        // 判断是否为空
        AssertUtil.notNull(source, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(source, OperateSourceVo.class));
    }

    @Override
    public Result editSource(OperateSourceQuery query) {
        // 判断来源渠道是否已存在
        Source sourceByName = sourceFunc.getSourceByName(query.getName());
        boolean checkName = sourceByName != null && !Objects.equals(sourceByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.SOURCE_REPEAT);
        // 封装修改
        Source source = new Source();
        source.setId(query.getId());
        source.setName(query.getName());
        // 修改
        sourceMapper.updateById(source);
        return Result.success();
    }

    @Override
    public Result deleteSource(Long sourceId) {
        // 封装修改
        Source source = new Source();
        source.setId(sourceId);
        source.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        sourceMapper.updateById(source);
        return Result.success();
    }
}
