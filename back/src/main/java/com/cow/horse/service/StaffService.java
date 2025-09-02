package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.OperateStaffQuery;
import com.cow.horse.entity.query.PageStaffByKeyQuery;

public interface StaffService {
    /**
     * 获取角色
     *
     * @return
     */
    Result getRole();

    /**
     * 分页查询员工
     *
     * @param pageStaffByKeyQuery
     * @return
     */
    Result pageStaffByKey(PageStaffByKeyQuery pageStaffByKeyQuery);

    /**
     * 新增员工
     *
     * @param operateStaffQuery
     * @return
     */
    Result addStaff(OperateStaffQuery operateStaffQuery);

    /**
     * 根据编号查询员工
     *
     * @param staffId
     * @return
     */
    Result getStaffById(Long staffId);

    /**
     * 编辑员工
     *
     * @param operateStaffQuery
     * @return
     */
    Result editStaff(OperateStaffQuery operateStaffQuery);

    /**
     * 离职员工
     *
     * @param staffId
     * @return
     */
    Result leaveStaff(Long staffId);
}
