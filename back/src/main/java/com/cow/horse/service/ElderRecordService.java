package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.PageElderByKeyQuery;

import java.io.IOException;

public interface ElderRecordService {
    /**
     * 导出excel
     */
    Result exportExcel() throws IOException;

    /**
     * 分页查询长者
     *
     * @param pageElderByKeyQuery
     * @return
     */
    Result pageElderByKey(PageElderByKeyQuery pageElderByKeyQuery);

    /**
     * 根据编号获取长者档案
     *
     * @param elderId
     * @return
     */
    Result getElderRecordById(Long elderId);
}
