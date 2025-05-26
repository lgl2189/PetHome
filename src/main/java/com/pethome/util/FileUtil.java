package com.pethome.util;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 26 15:31
 */


public class FileUtil {

    public static String replaceBackSlashToForwardSlash(String path) {
        return path.replace("\\", "/");
    }

}