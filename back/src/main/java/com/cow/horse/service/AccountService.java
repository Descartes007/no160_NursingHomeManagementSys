package com.cow.horse.service;

import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.query.EditQuery;
import com.cow.horse.entity.query.ForgetQuery;
import com.cow.horse.entity.query.LoginQuery;
import com.cow.horse.entity.query.SendCodeQuery;

public interface AccountService {
    /**
     * 登录
     *
     * @param loginQuery
     * @return
     */
    Result login(LoginQuery loginQuery);

    /**
     * 发送验证码
     *
     * @param sendCodeQuery
     * @return
     */
    Result sendCode(SendCodeQuery sendCodeQuery);

    /**
     * 忘记密码
     *
     * @param forgetQuery
     * @return
     */
    Result forget(ForgetQuery forgetQuery);

    /**
     * 修改密码
     * @param editQuery
     * @return
     */
    Result edit(EditQuery editQuery);

    /**
     * 登出
     *
     * @return
     */
    Result logout();
}
