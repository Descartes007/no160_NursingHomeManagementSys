package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.AddOrderQuery;
import com.cow.horse.entity.query.PageOrderByKeyQuery;
import com.cow.horse.entity.query.SendOrderQuery;

public interface OrderService {
    /**
     * 分页查询点餐
     *
     * @param pageOrderByKeyQuery
     * @return
     */
    Result pageOrderByKey(PageOrderByKeyQuery pageOrderByKeyQuery);

    /**
     * 新增点餐
     *
     * @param addOrderQuery
     * @return
     */
    Result addOrder(AddOrderQuery addOrderQuery);

    /**
     * 根据编号获取点餐
     *
     * @param orderId
     * @return
     */
    Result getOrderById(Long orderId);

    /**
     * 送餐
     *
     * @param sendOrderQuery
     * @return
     */
    Result sendOrder(SendOrderQuery sendOrderQuery);
}
