package com.pethome.constant;

import com.google.common.collect.Streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 30 15:45
 */


public class Constant {
    public static final String JWT_SECRET = "OzWsf2X24eKLZbvazvBMXqcrHd0RRhsCOibf+8KfQRc=";
    public static final byte[] JWT_SECRET_BYTE = JWT_SECRET.getBytes();
    // Redis的key的命名规范
    // 项目名:模块名:功能名:[唯一业务参数]
    public static final String REDIS_KEY_LOGIN_TOKEN = "login:token:";

    //请求参数
    public static final String REQUEST_USER_TOKEN_KEY = "token";

    // 接口相关
    public static final String USER_LOGIN_URL = "/user/public/login";
    public static final String USER_LOGOUT_URL = "/user/logout";

    public static final List<String> SWAGGER_URL_LIST = List.of(
            "/swagger-ui/index.html",
            "/swagger-ui.html",
            "/swagger-ui/index.css",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/swagger-initializer.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/index.css",
            "/v3/api-docs"
    );

    public static final List<String> IGNORE_URL_LIST = Streams.concat(
            SWAGGER_URL_LIST.stream(),
            Stream.of()
    ).collect(Collectors.toList());

    public static final List<String> SECURITY_IGNORE_URL_LIST = Streams.concat(
            IGNORE_URL_LIST.stream(),
            Stream.of()
    ).collect(Collectors.toList());

    public static final List<String> JWT_IGNORE_URL_LIST = Streams.concat(
            IGNORE_URL_LIST.stream(),
            Stream.of(
                    USER_LOGIN_URL
            )
    ).collect(Collectors.toList());

    // 权限相关
    public static final String ROLE_SUPER = "super";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_VOLUNTEER = "volunteer";
    public static final String ROLE_ADOPTER = "adopter";
    public static final String ROLE_DONATOR = "donator";
    public static final String ROLE_NORMAL = "normal";

    public static final Integer ROLE_SUPER_ID = 1;
    public static final Integer ROLE_ADMIN_ID = 2;
    public static final Integer ROLE_VOLUNTEER_ID = 3;
    public static final Integer ROLE_ADOPTER_ID = 4;
    public static final Integer ROLE_DONATOR_ID = 5;
    public static final Integer ROLE_NORMAL_ID = 6;

    // 文件相关
    public static final String UNKNOWN_FILE_TYPE = "unknown";
}