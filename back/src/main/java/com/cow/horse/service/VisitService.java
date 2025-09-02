package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.AddVisitQuery;
import com.cow.horse.entity.query.RecordLeaveQuery;
import com.cow.horse.entity.query.EditVisitQuery;
import com.cow.horse.entity.query.PageVisitByKeyQuery;

public interface VisitService {
    /**
     * 分页查询来访登记
     *
     * @param pageVisitByKeyQuery
     * @return
     */
    Result pageVisitByKey(PageVisitByKeyQuery pageVisitByKeyQuery);

    /**
     * 新增来访登记
     *
     * @param addVisitQuery
     * @return
     */
    Result addVisit(AddVisitQuery addVisitQuery);

    /**
     * 根据编号获取来访登记
     *
     * @param visitId
     * @return
     */
    Result getVisitById(Long visitId);

    /**
     * 编辑来访登记
     *
     * @param editVisitQuery
     * @return
     */
    Result editVisit(EditVisitQuery editVisitQuery);

    /**
     * 登记离开
     *
     * @param recordLeaveQuery
     * @return
     */
    Result recordLeave(RecordLeaveQuery recordLeaveQuery);

    /**
     * 删除来访登记
     *
     * @param visitId
     * @return
     */
    Result deleteVisit(Long visitId);
}
