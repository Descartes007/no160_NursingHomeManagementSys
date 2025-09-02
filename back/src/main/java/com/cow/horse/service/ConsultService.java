package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.GetConsultByConsultIdAndElderIdQuery;
import com.cow.horse.entity.query.OperateConsultQuery;
import com.cow.horse.entity.query.PageConsultByKeyQuery;

public interface ConsultService {
    /**
     * 获取来源渠道列表
     *
     * @return
     */
    Result listConsultSource();

    /**
     * 获取接待人列表
     *
     * @return
     */
    Result listConsultStaff();

    /**
     * 分页查询咨询
     *
     * @param pageConsultByKeyQuery
     * @return
     */
    Result pageConsultByKey(PageConsultByKeyQuery pageConsultByKeyQuery);

    /**
     * 新增咨询
     *
     * @param operateConsultQuery
     * @return
     */
    Result addConsult(OperateConsultQuery operateConsultQuery);

    /**
     * 根据咨询人编号和老人编号获取咨询信息
     *
     * @param getConsultByConsultIdAndElderIdQuery
     * @return
     */
    Result getConsultByConsultIdAndElderId(GetConsultByConsultIdAndElderIdQuery getConsultByConsultIdAndElderIdQuery);

    /**
     * 编辑咨询
     *
     * @param operateConsultQuery
     * @return
     */
    Result editConsult(OperateConsultQuery operateConsultQuery);

    /**
     * 删除咨询
     *
     * @param elderId
     * @return
     */
    Result deleteConsult(Long elderId);

    /**
     * 转为意向客户
     *
     * @param elderId
     * @return
     */
    Result intentionConsult(Long elderId);
}
