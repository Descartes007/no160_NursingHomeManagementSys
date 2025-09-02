package com.cow.horse.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.dao.mapper.WarehouseMapper;
import com.cow.horse.entity.po.Warehouse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 仓库表公共方法
 */
@Component
public class WarehouseFunc {
    @Resource
    private WarehouseMapper warehouseMapper;

    /**
     * 获取未被删除的仓库列表
     *
     * @return
     */
    public List<Warehouse> listNotDelWarehouse() {
        return warehouseMapper.selectList(new LambdaQueryWrapper<Warehouse>()
                .eq(Warehouse::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 根据仓库名称获取仓库
     *
     * @param warehouseName
     * @return
     */
    public Warehouse getWarehouseByName(String warehouseName) {
        return warehouseMapper.selectOne(new LambdaQueryWrapper<Warehouse>()
                .eq(Warehouse::getName, warehouseName)
                .eq(Warehouse::getDelFlag, YesNoEnum.NO.getCode()));
    }
}
