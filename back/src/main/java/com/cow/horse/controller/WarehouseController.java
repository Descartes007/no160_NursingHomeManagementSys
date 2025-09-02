package com.cow.horse.controller;

import com.cow.horse.common.constant.Constant;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.PageWarehouseByKeyQuery;
import com.cow.horse.entity.query.OperateWarehouseQuery;
import com.cow.horse.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "仓库设置")
@RestController
@RequestMapping("/warehouse")
@PreAuthorize("@AuthorityAssert.hasAuthority('/resource/set')")
public class WarehouseController {
    @Resource
    private WarehouseService warehouseService;

    @GetMapping("/pageWarehouseByKey")
    @ApiOperation(value = "分页查询仓库", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageWarehouseByKey(@ApiParam(value = "分页查询仓库请求实体", required = true) PageWarehouseByKeyQuery pageWarehouseByKeyQuery,
                                     @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseService.pageWarehouseByKey(pageWarehouseByKeyQuery);
    }

    @GetMapping("/listWarehouseStaff")
    @ApiOperation(value = "仓库管理员", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result listWarehouseStaff(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseService.listWarehouseStaff();
    }

    @PostMapping("/addWarehouse")
    @ApiOperation(value = "新增仓库", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result addWarehouse(@ApiParam(value = "新增仓库请求实体", required = true) @RequestBody OperateWarehouseQuery operateWarehouseQuery,
                               @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseService.addWarehouse(operateWarehouseQuery);
    }

    @GetMapping("/getWarehouseById")
    @ApiOperation(value = "根据编号查询仓库", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result getWarehouseById(@ApiParam(value = "根据编号查询仓库请求参数", required = true) @RequestParam Long warehouseId,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseService.getWarehouseById(warehouseId);
    }

    @PutMapping("/editWarehouse")
    @ApiOperation(value = "编辑仓库", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result editWarehouse(@ApiParam(value = "编辑仓库请求实体", required = true) @RequestBody OperateWarehouseQuery operateWarehouseQuery,
                                @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseService.editWarehouse(operateWarehouseQuery);
    }

    @DeleteMapping("/deleteWarehouse")
    @ApiOperation(value = "删除仓库", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result deleteWarehouse(@ApiParam(value = "删除仓库请求参数", required = true) @RequestParam Long warehouseId,
                                  @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return warehouseService.deleteWarehouse(warehouseId);
    }
}
