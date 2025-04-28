package com.pethome.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 28 18:15
 */


public class EncryptExample {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "1";
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("加密后的密码: " + encryptedPassword);
    }
}