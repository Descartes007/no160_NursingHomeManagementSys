package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.PageInventoryByKeyQuery;

public interface InventoryService {
    /**
     * 分页查询库存
     *
     * @param pageInventoryByKeyQuery
     * @return
     */
    Result pageInventoryByKey(PageInventoryByKeyQuery pageInventoryByKeyQuery);
}
