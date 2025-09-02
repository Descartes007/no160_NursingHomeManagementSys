package com.cow.horse.service.impl;

import com.cow.horse.common.util.PageUtil;
import com.cow.horse.dao.mapper.WarehouseMaterialMapper;
import com.cow.horse.entity.base.PageResult;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.PageInventoryByKeyQuery;
import com.cow.horse.entity.vo.PageInventoryByKeyVo;
import com.cow.horse.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Resource
    private WarehouseMaterialMapper warehouseMaterialMapper;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageInventoryByKey(PageInventoryByKeyQuery query) {
        // 根据关键字获取库存列表
        List<PageInventoryByKeyVo> pageIntentionByKeyVoList = warehouseMaterialMapper.listInventoryByKey(query);
        // 将库存列表根据物资编号分组
        Map<Long, List<PageInventoryByKeyVo>> materialInventoryMap = pageIntentionByKeyVoList.parallelStream()
                .collect(Collectors.groupingBy(PageInventoryByKeyVo::getMaterialId));
        // 设置总库存
        pageIntentionByKeyVoList.forEach(pageInventoryByKeyVo -> {
            Integer[] total = {0};
            materialInventoryMap.get(pageInventoryByKeyVo.getMaterialId()).forEach(inventory ->
                    total[0] += inventory.getInventory()
            );
            pageInventoryByKeyVo.setTotal(total[0]);
        });
        // 封装返回数据
        PageResult<PageInventoryByKeyVo> pageResult = pageUtil.packPageResultData(pageIntentionByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }
}
