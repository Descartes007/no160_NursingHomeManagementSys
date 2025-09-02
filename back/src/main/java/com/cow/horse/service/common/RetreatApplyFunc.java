package com.cow.horse.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cow.horse.common.constant.AuditEnum;
import com.cow.horse.dao.mapper.RetreatApplyMapper;
import com.cow.horse.entity.po.RetreatApply;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 退住申请表公共方法
 */
@Component
public class RetreatApplyFunc {
    @Resource
    private RetreatApplyMapper retreatApplyMapper;

    /**
     * 获取未审核的退住申请
     *
     * @return
     */
    public List<RetreatApply> listNotAuditApply() {
        return retreatApplyMapper.selectList(new LambdaQueryWrapper<RetreatApply>()
                .eq(RetreatApply::getApplyFlag, AuditEnum.STAY_AUDIT.getStatus())
        );
    }
}
