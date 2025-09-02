package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.PageSearchElderByKeyQuery;
import com.cow.horse.entity.query.PageRetreatApplyQuery;

public interface RetreatApplyService {
    /**
     * 分页查询退住申请
     *
     * @param pageRetreatApplyQuery
     * @return
     */
    Result pageRetreatApplyByKey(PageRetreatApplyQuery pageRetreatApplyQuery);

    /**
     * 分页搜索老人
     *
     * @param pageSearchElderByKeyQuery
     * @return
     */
    Result pageSearchElderByKey(PageSearchElderByKeyQuery pageSearchElderByKeyQuery);

    /**
     * 新增退住申请
     *
     * @param elderId
     * @return
     */
    Result addRetreatApply(Long elderId);
}
