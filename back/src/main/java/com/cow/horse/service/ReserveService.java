package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.GetReserveByReserveIdAndElderIdQuery;
import com.cow.horse.entity.query.PageReserveByKeyQuery;
import com.cow.horse.entity.query.PageSearchElderByKeyQuery;
import com.cow.horse.entity.query.AddReserveQuery;

public interface ReserveService {
    /**
     * 分页查询预定
     *
     * @param pageReserveByKeyQuery
     * @return
     */
    Result pageReserveByKey(PageReserveByKeyQuery pageReserveByKeyQuery);

    /**
     * 分页搜索老人
     *
     * @param pageSearchElderByKeyQuery
     * @return
     */
    Result pageSearchElderByKey(PageSearchElderByKeyQuery pageSearchElderByKeyQuery);

    /**
     * 获取楼栋树
     *
     * @return
     */
    Result getBuildTree();

    /**
     * 新增预定
     *
     * @param addReserveQuery
     * @return
     */
    Result addReserve(AddReserveQuery addReserveQuery);

    /**
     * 根据预定编号和老人编号获取预定信息
     *
     * @param getReserveByReserveIdAndElderIdQuery
     * @return
     */
    Result getReserveByReserveIdAndElderId(GetReserveByReserveIdAndElderIdQuery getReserveByReserveIdAndElderIdQuery);

    /**
     * 退款
     *
     * @param reserveId
     * @return
     */
    Result refund(Long reserveId);

    /**
     * 床位预定过期任务
     */
    void reserveExpireJob();
}
