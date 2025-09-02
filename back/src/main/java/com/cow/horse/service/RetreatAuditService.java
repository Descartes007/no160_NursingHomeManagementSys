package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.AuditElderFeeQuery;
import com.cow.horse.entity.query.PageRetreatAuditQuery;

public interface RetreatAuditService {
    /**
     * 分页查询退住审核
     *
     * @param pageRetreatAuditQuery
     * @return
     */
    Result pageRetreatAuditByKey(PageRetreatAuditQuery pageRetreatAuditQuery);

    /**
     * 根据编号获取老人费用详情
     *
     * @param elderId
     * @return
     */
    Result getElderFeeById(Long elderId);

    /**
     * @param auditElderFeeQuery
     * @return
     */
    Result auditElderFee(AuditElderFeeQuery auditElderFeeQuery);
}
