package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.PageSearchElderByKeyQuery;
import com.cow.horse.entity.query.RechargeQuery;
import com.cow.horse.entity.query.PageDepositRechargeByKeyQuery;

public interface DepositRechargeService {
    /**
     * 分页查询预存充值
     *
     * @param pageDepositRechargeByKeyQuery
     * @return
     */
    Result pageDepositRechargeByKey(PageDepositRechargeByKeyQuery pageDepositRechargeByKeyQuery);

    /**
     * 分页搜索老人
     *
     * @param pageSearchElderByKeyQuery
     * @return
     */
    Result pageSearchElderByKey(PageSearchElderByKeyQuery pageSearchElderByKeyQuery);

    /**
     * 入住老人账户充值
     *
     * @param rechargeQuery
     * @return
     */
    Result recharge(RechargeQuery rechargeQuery);
}
