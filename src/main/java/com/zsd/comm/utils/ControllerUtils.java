package com.zsd.comm.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器工具类.
 * @author zhousd
 */
public final class ControllerUtils {

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_ERROR = "500";

    public static Map<String, Object> responseBuilder(String code, String msg) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("msg", msg);
        return response;
    }

    private ControllerUtils() {}
}
