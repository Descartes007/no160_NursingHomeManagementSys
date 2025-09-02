package com.cow.horse.service.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.horse.dao.mapper.WarehouseMaterialMapper;
import com.cow.horse.entity.po.WarehouseMaterial;
import org.springframework.stereotype.Component;

/**
 * 入库物资表公共方法
 */
@Component
public class WarehouseMaterialFunc extends ServiceImpl<WarehouseMaterialMapper, WarehouseMaterial> {
}
