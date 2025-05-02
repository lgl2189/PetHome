package com.pethome.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.SecureRandom;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 5月 02 14:43
 */


public class JwtSecretGenerator {
    public static void main(String[] args) {
        // 创建一个SecureRandom实例用于生成随机字节
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];// 生成32字节的随机字节，对应256位
        secureRandom.nextBytes(keyBytes);

        // 使用Base64对生成的字节数组进行编码，得到一个可打印的字符串形式的密钥
        String jwtSecret = Base64.encodeBase64String(keyBytes);
        System.out.println("生成的JWT密钥: " + jwtSecret);
    }
}