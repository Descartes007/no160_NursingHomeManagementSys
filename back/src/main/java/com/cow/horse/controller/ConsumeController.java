package com.cow.horse.controller;

import com.cow.horse.common.constant.Constant;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.PageConsumeByKeyQuery;
import com.cow.horse.service.ConsumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "消费记录")
@RestController
@RequestMapping("/consume")
@PreAuthorize("@AuthorityAssert.hasAuthority('/fee/record')")
public class ConsumeController {
    @Resource
    private ConsumeService consumeService;

    @GetMapping("/pageConsumeByKey")
    @ApiOperation(value = "分页查询消费记录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result pageConsumeByKey(@ApiParam(value = "分页查询消费记录请求实体", required = true) PageConsumeByKeyQuery pageConsumeByKeyQuery,
                                   @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return consumeService.pageConsumeByKey(pageConsumeByKeyQuery);
    }
}
