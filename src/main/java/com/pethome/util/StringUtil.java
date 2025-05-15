package com.pethome.util;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 15 10:37
 */


public class StringUtil {
    /**
     * 判断字符串是否为蛇形命名法
     * @param source 原字符串
     * @return true：蛇形；false：非蛇形
     */
    public static boolean isSnake(String source){
        if(source == null || source.isEmpty()){
            return false;
        }
        // 检查是否符合蛇形命名法的正则表达式
        // ^[a-z_][a-z0-9_]*$ 确保字符串由小写字母、数字和下划线组成，且不以数字开头
        // (?<!_) 负向后行断言确保没有连续的下划线
        // .*_.* 确保至少有一个下划线
        return source.matches("^[a-z][a-z0-9]*_(?!_)([a-z0-9]+(?:_[a-z0-9]+)*)$") && source.contains("_");
    }

    /**
     * 将蛇形命名法转换为驼峰命名法
     * @param source 蛇形命名法的字符串
     * @return 驼峰命名法的字符串
     */
    public static String convertSnakeToCamel(String source) {
        // 使用正则表达式分割字符串并转换为驼峰
        String[] parts = source.split("_");
        StringBuilder result = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            if (!parts[i].isEmpty()) {
                result.append(Character.toUpperCase(parts[i].charAt(0)))
                        .append(parts[i].substring(1));
            }
        }
        return result.toString();
    }
}