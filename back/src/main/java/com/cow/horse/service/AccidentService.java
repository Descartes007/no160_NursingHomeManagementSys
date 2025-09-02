package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.AddAccidentQuery;
import com.cow.horse.entity.query.EditAccidentQuery;
import com.cow.horse.entity.query.PageAccidentByKeyQuery;

public interface AccidentService {
    /**
     * 分页查询事故登记
     *
     * @param pageAccidentByKeyQuery
     * @return
     */
    Result pageAccidentByKey(PageAccidentByKeyQuery pageAccidentByKeyQuery);

    /**
     * 新增事故登记
     *
     * @param addAccidentQuery
     * @return
     */
    Result addAccident(AddAccidentQuery addAccidentQuery);

    /**
     * 根据编号获取事故登记
     *
     * @param accidentId
     * @return
     */
    Result getAccidentById(Long accidentId);

    /**
     * 编辑事故登记
     *
     * @param editAccidentQuery
     * @return
     */
    Result editAccident(EditAccidentQuery editAccidentQuery);

    /**
     * 删除事故登记
     *
     * @param accidentId
     * @return
     */
    Result deleteAccident(Long accidentId);
}
