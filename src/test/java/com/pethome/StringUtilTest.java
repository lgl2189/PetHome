package com.pethome;

import com.pethome.util.StringUtil;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 13 16:36
 */


public class StringUtilTest {

    public static void main(String[] args) {
        String snakeStr = "snake_case_Aaa";
        String camelStr = StringUtil.snakeToCamel(snakeStr);
        System.out.println(camelStr);
    }

}