package com.cow.horse.service.impl;

import com.cow.horse.common.constant.CheckEnum;
import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.common.util.AssertUtil;
import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.ElderMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.po.Elder;
import com.cow.horse.entity.query.PageSearchElderByKeyQuery;
import com.cow.horse.entity.query.RechargeQuery;
import com.cow.horse.entity.vo.PageDepositRechargeByKeyVo;
import com.cow.horse.service.DepositRechargeService;
import com.cow.horse.service.common.CommonFunc;
import com.cow.horse.entity.query.PageDepositRechargeByKeyQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DepositRechargeServiceImpl implements DepositRechargeService {
    @Resource
    private CommonFunc commonFunc;
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageDepositRechargeByKey(PageDepositRechargeByKeyQuery query) {
        // 根据搜索关键字查询预存充值信息
        List<PageDepositRechargeByKeyVo> pageDepositRechargeByKeyVoList = elderMapper.listDepositRechargeByKey(query);
        // 封装返回数据
        PageResult<PageDepositRechargeByKeyVo> pageResult = pageUtil.packPageResultData(pageDepositRechargeByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result pageSearchElderByKey(PageSearchElderByKeyQuery query) {
        List<String> checkFlagList = new ArrayList<>(Arrays.asList(CheckEnum.ENTER.getStatus(), CheckEnum.EXIT_AUDIT.getStatus()));
        // 根据姓名和联系电话获取入住和退住审核老人列表
        return commonFunc.pageSearchElderByKeyResult(query,checkFlagList);
    }

    @Override
    public Result recharge(RechargeQuery query) {
        // 判断是否是入住老人
        Elder elder = elderMapper.selectById(query.getElderId());
        boolean checkFlag = Objects.equals(elder.getCheckFlag(), CheckEnum.ENTER.getStatus()) ||
                Objects.equals(elder.getCheckFlag(), CheckEnum.EXIT_AUDIT.getStatus());
        AssertUtil.isTrue(checkFlag, ExceptionEnum.NOT_ENTER);
        // 封装修改
        elder.setBalance(query.getAmount().add(elder.getBalance()));
        // 修改
        elderMapper.updateById(elder);
        return Result.success();
    }
}
