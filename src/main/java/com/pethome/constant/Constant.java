package com.pethome.constant;

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
    public static final String REDIS_TOKEN_KEY = "pethome:login:token";

    public static final String USER_LOGIN_URL = "/user/login";
    public static final String USER_LOGOUT_URL = "/user/logout";
    public static final String USER_REGISTER_URL = "/user/regist";

    // 权限相关
    public static final String ROLE_SUPER="super";
    public static final String ROLE_ADMIN="admin";
    public static final String ROLE_VOLUNTEER="volunteer";
    public static final String ROLE_ADOPTER="adopter";
    public static final String ROLE_DONATOR="donator";
    public static final String ROLE_NORMAL="normal";
    public static final String ROLE_VISITOR="visitor";
}