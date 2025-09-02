package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.AddNurseReserveQuery;
import com.cow.horse.entity.query.ExecuteNurseReserveQuery;
import com.cow.horse.entity.query.PageNurseReserveByKeyQuery;

public interface NurseReserveService {
    /**
     * 分页查询护理预定
     *
     * @param PageNurseReserveByKeyQuery
     * @return
     */
    Result pageNurseReserveByKey(PageNurseReserveByKeyQuery PageNurseReserveByKeyQuery);

    /**
     * 获取服务项目
     *
     * @return
     */
    Result listService();

    /**
     * 新增护理预定
     *
     * @param addNurseReserveQuery
     * @return
     */
    Result addNurseReserve(AddNurseReserveQuery addNurseReserveQuery);

    /**
     * 护理人员
     *
     * @return
     */
    Result listNurseStaff();

    /**
     * 执行护理预定
     *
     * @param executeNurseReserve
     * @return
     */
    Result executeNurseReserve(ExecuteNurseReserveQuery executeNurseReserve);
}
