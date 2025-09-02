package com.cow.horse.service.common;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.dao.mapper.RoomTypeMapper;
import com.cow.horse.entity.po.RoomType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房间类型表公共方法
 */
@Component
public class RoomTypeFunc {
    @Resource
    private RoomTypeMapper roomTypeMapper;

    /**
     * 获取所有未被删除的房间类型
     *
     * @return
     */
    public List<RoomType> listNotDelRoomType(String searchKey) {
        return roomTypeMapper.selectList(new LambdaQueryWrapper<RoomType>()
                .and(roomTypeLambdaQueryWrapper -> {
                    roomTypeLambdaQueryWrapper.eq(RoomType::getDelFlag, YesNoEnum.NO.getCode());
                    if (ObjUtil.isNotEmpty(searchKey)) {
                        roomTypeLambdaQueryWrapper.like(RoomType::getName, searchKey);
                    }
                })
                .orderByDesc(RoomType::getCreateTime)
        );
    }

    /**
     * 根据名称获取活动分类
     *
     * @return
     */
    public RoomType getRoomTypeByName(String RoomTypeName) {
        return roomTypeMapper.selectOne(new LambdaQueryWrapper<RoomType>()
                .eq(RoomType::getName, RoomTypeName)
                .eq(RoomType::getDelFlag, YesNoEnum.NO.getCode()));
    }
}
