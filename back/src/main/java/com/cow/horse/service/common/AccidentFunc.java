package com.cow.horse.service.common;

import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.common.util.AssertUtil;
import com.cow.horse.dao.mapper.AccidentMapper;
import com.cow.horse.entity.po.Accident;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 事故登记表公共方法
 */
@Component
public class AccidentFunc {
    @Resource
    private AccidentMapper accidentMapper;

    /**
     * 验证事故登记
     *
     * @param accidentId
     * @param delFlag
     * @return
     */
    public Accident checkAccident(Long accidentId, Boolean delFlag) {
        // 根据编号获取事故登记
        Accident accident = accidentMapper.selectById(accidentId);
        // 验证事故登记是否存在
        AssertUtil.notNull(accident, ExceptionEnum.DATA_NOT_EXIST);
        // 验证事故登记是否已被删除
        boolean checkDel = Objects.equals(accident.getDelFlag(), YesNoEnum.YES.getCode());
        AssertUtil.notTrue(checkDel, delFlag ? ExceptionEnum.DEL_REPEAT : ExceptionEnum.RECORD_ALREADY_DELETE);
        return accident;
    }
}
