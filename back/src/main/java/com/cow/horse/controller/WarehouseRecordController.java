package com.cow.horse.controller;

import com.cow.horse.common.constant.Constant;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.AddWarehouseRecordQuery;
import com.cow.horse.entity.query.AuditWarehouseRecordQuery;
import com.cow.horse.entity.query.PageMaterialByKeyQuery;
import com.cow.horse.entity.query.PageWarehouseRecordByKeyQuery;
import com.cow.horse.entity.query.*;
import com.cow.horse.service.MaterialService;
import com.cow.horse.service.WarehouseRecordService;
import com.cow.horse.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "入库管理")
@RestController
@RequestMapping("/warehouseRecord")
@PreAuthorize("@AuthorityAssert.hasAuthority('/resource/enter')")
public class WarehouseRecordController {
    @Resource
    private WarehouseRecordService warehouseRecordService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private MaterialService materialService;

    @GetMapping("/pageWarehouseRecordByKey")
    @ApiOperation(value = "分页查询入库记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageWarehouseRecordByKey(@ApiParam(value = "分页查询入库记录请求实体", required = true) PageWarehouseRecordByKeyQuery pageWarehouseRecordByKeyQuery,
                                           @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseRecordService.pageWarehouseRecordByKey(pageWarehouseRecordByKeyQuery);
    }

    @GetMapping("/listWarehouse")
    @ApiOperation(value = "获取仓库列表", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listWarehouse(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseRecordService.listWarehouse();
    }

    @GetMapping("/listWarehouseStaff")
    @ApiOperation(value = "获取经办人", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listWarehouseStaff(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseService.listWarehouseStaff();
    }

    @GetMapping("/pageMaterialByKey")
    @ApiOperation(value = "分页查询物资", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageMaterialByKey(@ApiParam(value = "分页查询物资请求实体", required = true) PageMaterialByKeyQuery pageMaterialByKeyQuery,
                                    @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return materialService.pageMaterialByKey(pageMaterialByKeyQuery);
    }

    @PostMapping("/addWarehouseRecord")
    @ApiOperation(value = "新增入库记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addWarehouseRecord(@ApiParam(value = "新增入库记录请求实体", required = true) @RequestBody AddWarehouseRecordQuery addWarehouseRecordQuery,
                                     @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseRecordService.addWarehouseRecord(addWarehouseRecordQuery);
    }

    @GetMapping("/getWarehouseRecordById")
    @ApiOperation(value = "根据编号查询入库记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getWarehouseRecordById(@ApiParam(value = "根据编号查询入库记录请求参数", required = true) @RequestParam Long warehouseRecordId,
                                         @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseRecordService.getWarehouseRecordById(warehouseRecordId);
    }

    @PutMapping("/auditWarehouseRecord")
    @ApiOperation(value = "审核入库记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result auditWarehouseRecord(@ApiParam(value = "审核入库记录请求实体", required = true) @RequestBody AuditWarehouseRecordQuery auditWarehouseRecordQuery,
                                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseRecordService.auditWarehouseRecord(auditWarehouseRecordQuery);
    }

    @DeleteMapping("/deleteWarehouseRecord")
    @ApiOperation(value = "删除入库记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteWarehouseRecord(@ApiParam(value = "删除入库记录请求参数", required = true) @RequestParam Long warehouseRecordId,
                                        @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseRecordService.deleteWarehouseRecord(warehouseRecordId);
    }
}
