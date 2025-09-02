package com.cow.horse.common.util;

import cn.hutool.json.JSONUtil;
import com.cow.horse.common.constant.ExceptionEnum;
import com.cow.horse.entity.base.Result;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ResponseUtil {
    public static void resultOut(HttpServletResponse response, Result result) {
        String errorJson = JSONUtil.toJsonStr(result);
        out(response, errorJson);
    }

    public static void exceptionEnumOut(HttpServletResponse response, ExceptionEnum exceptionEnum) {
        String errorJson = JSONUtil.toJsonStr(Result.error(exceptionEnum));
        out(response, errorJson);
    }

    private static void out(HttpServletResponse response, String errorJson) {
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Content-Type", "application/json;charset=utf-8");
        try {
            response.getWriter().write(errorJson);
            response.flushBuffer();
        } catch (IOException e) {
            log.warn("Write data failed.", e);
        }
    }
}
