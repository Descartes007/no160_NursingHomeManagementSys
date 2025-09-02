package com.cow.horse.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.dao.mapper.MaterialMapper;
import com.cow.horse.entity.po.Material;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 物资表公共方法
 */
@Component
public class MaterialFunc {
    @Resource
    private MaterialMapper materialMapper;

    /**
     * 根据物资名称获取物资
     *
     * @param typeId
     * @param materialName
     * @return
     */
    public Material getMaterialByName(Long typeId, String materialName) {
        return materialMapper.selectOne(new LambdaQueryWrapper<Material>()
                .eq(Material::getTypeId, typeId)
                .eq(Material::getName, materialName)
                .eq(Material::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 判断物资分类下是否存在物资
     *
     * @return
     */
    public Boolean checkMaterialItem(Long typeId) {
        Long materialTotal = materialMapper.selectCount(new LambdaQueryWrapper<Material>()
                .eq(Material::getTypeId, typeId)
                .eq(Material::getDelFlag, YesNoEnum.NO.getCode()));
        return materialTotal > 0;
    }
}
