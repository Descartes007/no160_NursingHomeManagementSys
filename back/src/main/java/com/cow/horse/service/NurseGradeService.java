package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.PageNurseGradeByKeyQuery;
import com.cow.horse.entity.query.PageServiceByKeyQuery;
import com.cow.horse.entity.query.OperateNurseGradeQuery;

public interface NurseGradeService {
    /**
     * 分页查询护理等级
     *
     * @param pageNurseGradeByKeyQuery
     * @return
     */
    Result pageNurseGradeByKey(PageNurseGradeByKeyQuery pageNurseGradeByKeyQuery);

    /**
     * 分页查询服务
     *
     * @param pageServiceByKeyQuery
     * @return
     */
    Result pageServiceByKey(PageServiceByKeyQuery pageServiceByKeyQuery);

    /**
     * 新增护理等级
     *
     * @param operateNurseGradeQuery
     * @return
     */
    Result addNurseGrade(OperateNurseGradeQuery operateNurseGradeQuery);

    /**
     * 根据编号查询护理等级
     *
     * @param nurseGradeId
     * @return
     */
    Result getNurseGradeById(Long nurseGradeId);

    /**
     * 编辑护理等级
     *
     * @param operateNurseGradeQuery
     * @return
     */
    Result editNurseGrade(OperateNurseGradeQuery operateNurseGradeQuery);

    /**
     * 删除护理等级
     *
     * @param nurseGradeId
     * @return
     */
    Result deleteNurseGrade(Long nurseGradeId);
}
