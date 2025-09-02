package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.OperateWarehouseQuery;
import com.cow.horse.entity.query.PageWarehouseByKeyQuery;

public interface WarehouseService {
    /**
     * 分页查询仓库
     *
     * @param pageWarehouseByKeyQuery
     * @return
     */
    Result pageWarehouseByKey(PageWarehouseByKeyQuery pageWarehouseByKeyQuery);

    /**
     * 仓库管理员
     *
     * @return
     */
    Result listWarehouseStaff();

    /**
     * 新增仓库
     *
     * @param operateWarehouseQuery
     * @return
     */
    Result addWarehouse(OperateWarehouseQuery operateWarehouseQuery);

    /**
     * 根据编号查询仓库
     *
     * @param warehouseId
     * @return
     */
    Result getWarehouseById(Long warehouseId);

    /**
     * 编辑仓库
     *
     * @param operateWarehouseQuery
     * @return
     */
    Result editWarehouse(OperateWarehouseQuery operateWarehouseQuery);

    /**
     * 删除仓库
     *
     * @param warehouseId
     * @return
     */
    Result deleteWarehouse(Long warehouseId);
}
