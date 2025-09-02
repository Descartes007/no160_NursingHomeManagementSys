package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.OperateRoomTypeQuery;
import com.cow.horse.entity.query.PageRoomTypeByKeyQuery;

public interface RoomTypeService {
    /**
     * 分页查询房间类型
     *
     * @param pageRoomTypeByKeyQuery
     * @return
     */
    Result pageRoomTypeByKey(PageRoomTypeByKeyQuery pageRoomTypeByKeyQuery);

    /**
     * 新增房间类型
     *
     * @param operateRoomTypeQuery
     * @return
     */
    Result addRoomType(OperateRoomTypeQuery operateRoomTypeQuery);

    /**
     * 根据编号获取房间类型
     *
     * @param roomTypeId
     * @return
     */
    Result getRoomTypeById(Long roomTypeId);

    /**
     * 编辑房间类型
     *
     * @param operateRoomTypeQuery
     * @return
     */
    Result editRoomType(OperateRoomTypeQuery operateRoomTypeQuery);

    /**
     * 删除房间类型
     *
     * @param roomTypeId
     * @return
     */
    Result deleteRoomType(Long roomTypeId);
}
