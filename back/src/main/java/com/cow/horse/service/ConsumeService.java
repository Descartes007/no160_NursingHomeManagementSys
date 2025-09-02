package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.PageConsumeByKeyQuery;

public interface ConsumeService {
    /**
     * 分页查询消费记录
     *
     * @param pageConsumeByKeyQuery
     * @return
     */
    Result pageConsumeByKey(PageConsumeByKeyQuery pageConsumeByKeyQuery);
}
