package com.cow.horse.service.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.horse.dao.mapper.RoleAuthMapper;
import com.cow.horse.entity.po.RoleAuth;
import org.springframework.stereotype.Component;

/**
 * 角色权限表公共方法
 */
@Component
public class RoleAuthFunc extends ServiceImpl<RoleAuthMapper, RoleAuth> {
}
