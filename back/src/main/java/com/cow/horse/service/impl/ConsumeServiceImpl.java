package com.cow.horse.service.impl;

import com.cow.horse.common.util.DateUtilWen;
import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.ConsumeMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.PageConsumeByKeyQuery;
import com.cow.horse.entity.vo.PageConsumeByKeyVo;
import com.cow.horse.service.ConsumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ConsumeServiceImpl implements ConsumeService {
    @Resource
    private ConsumeMapper consumeMapper;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageConsumeByKey(PageConsumeByKeyQuery query) {
        // 获取开始/结束时间
        Date startTime = DateUtilWen.getDayStartTime(DateUtilWen.dateStrToDate(query.getStartTime()));
        Date endTime = DateUtilWen.getDayEndTime(DateUtilWen.dateStrToDate(query.getEndTime()));
        // 根据搜索关键字获取消费记录
        List<PageConsumeByKeyVo> pageConsumeByKeyVoList = consumeMapper.listConsumeByKey(query.getElderName(), startTime, endTime);
        // 封装返回数据
        PageResult<PageConsumeByKeyVo> pageResult = pageUtil.packPageResultData(pageConsumeByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }
}
