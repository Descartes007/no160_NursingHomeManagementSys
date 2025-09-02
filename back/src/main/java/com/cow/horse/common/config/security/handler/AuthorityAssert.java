package com.cow.horse.common.config.security.handler;

import cn.hutool.core.util.ObjUtil;
import com.cow.horse.entity.vo.LoginUserVo;
import com.cow.horse.common.constant.Constant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 自定义权限断言
 */
@Component("AuthorityAssert")
public class AuthorityAssert {
    public LoginUserVo getLoginUserInfo() {
        // 获取登录用户消息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ObjUtil.isEmpty(authentication) || Objects.equals(authentication.getPrincipal(), Constant.ANONYMOUS_USER) ?
                null :
                (LoginUserVo) authentication.getPrincipal();
    }

    public Long getLoginUserId() {
        return getLoginUserInfo().getId();
    }

    public Boolean hasAuthority(String authority) {
        // 获取当前用户的权限
        List<String> authList = getLoginUserInfo().getAuthUrlList();
        // 判断用户权限集合中是否存在authority
        return authList.contains(authority);
    }
}
