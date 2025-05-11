package com.pethome.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 11 15:16
 */


public class JwtTest {
    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoie1widXNlcklkXCI6MSxcInVzZXJQYXNzd29yZFwiOm51bGwsXCJjaGluYUlkXCI6bnVsbCxcInJlYWxOYW1lXCI6bnVsbCxcImJpcnRoRGF0ZVwiOlsyMDIwLDIsMV0sXCJwaG9uZVwiOlwiMVwiLFwiZW1haWxcIjpcIjFcIixcInJvbGVMaXN0XCI6W3tcInJvbGVJZFwiOjEsXCJyb2xlVGFnXCI6XCJzdXBlclwiLFwicm9sZU5hbWVcIjpcIui2hee6p-euoeeQhuWRmFwifV0sXCJleHBpcmVEYXRlVGltZVwiOlsyMDI1LDUsMTAsMTEsMzYsNDIsODMzMTAxODAwXSxcInVzZXJOYW1lXCI6XCIxXCJ9In0.x_n_ZOrx7Df4SRKJVfEYYvfCEmM1S6tyuvCSFMiEfV4";
        String userJson = JWT.of(token).getPayloads().get("user", String.class);
        System.out.println(userJson);
    }
}