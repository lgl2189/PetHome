package com.pethome.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 16 17:39
 */

public class MimeTypeUtil {
    private static final Map<String, String> MIME_TYPE_MAP = new HashMap<>();

    static {
        // 图片类型
        MIME_TYPE_MAP.put("image/jpeg", "jpg");
        MIME_TYPE_MAP.put("image/png", "png");
        MIME_TYPE_MAP.put("image/gif", "gif");
        MIME_TYPE_MAP.put("image/bmp", "bmp");
        MIME_TYPE_MAP.put("image/webp", "webp");
        MIME_TYPE_MAP.put("image/svg+xml", "svg");

        // 文档类型
        MIME_TYPE_MAP.put("application/pdf", "pdf");
        MIME_TYPE_MAP.put("application/msword", "doc");
        MIME_TYPE_MAP.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
        MIME_TYPE_MAP.put("application/vnd.ms-excel", "xls");
        MIME_TYPE_MAP.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
        MIME_TYPE_MAP.put("application/vnd.ms-powerpoint", "ppt");
        MIME_TYPE_MAP.put("application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx");

        // 文本类型
        MIME_TYPE_MAP.put("text/plain", "txt");
        MIME_TYPE_MAP.put("text/csv", "csv");
        MIME_TYPE_MAP.put("text/xml", "xml");
        MIME_TYPE_MAP.put("application/json", "json");

        // 视频类型
        MIME_TYPE_MAP.put("video/mp4", "mp4");
        MIME_TYPE_MAP.put("video/mpeg", "mpeg");
        MIME_TYPE_MAP.put("video/quicktime", "mov");
        MIME_TYPE_MAP.put("video/webm", "webm");

        // 音频类型
        MIME_TYPE_MAP.put("audio/mpeg", "mp3");
        MIME_TYPE_MAP.put("audio/wav", "wav");
        MIME_TYPE_MAP.put("audio/ogg", "ogg");
        MIME_TYPE_MAP.put("audio/mp4", "m4a");
    }

    /**
     * 根据MIME类型获取文件扩展名
     * @param mimeType MIME类型（如"image/jpeg"）
     * @return 文件扩展名（如"jpg"），未知类型返回"unknown"
     */
    public static String getFileExtension(String mimeType) {
        if (mimeType == null || mimeType.isEmpty()) {
            return "unknown";
        }

        // 尝试直接从映射表获取
        String extension = MIME_TYPE_MAP.get(mimeType.toLowerCase());
        if (extension != null) {
            return extension;
        }

        // 处理未知MIME类型：尝试从MIME类型中提取（如"application/x-custom" → "custom"）
        int lastSlashIndex = mimeType.lastIndexOf('/');
        if (lastSlashIndex != -1 && lastSlashIndex < mimeType.length() - 1) {
            String subtype = mimeType.substring(lastSlashIndex + 1);
            // 移除可能的参数（如"xml;charset=utf-8" → "xml"）
            int semicolonIndex = subtype.indexOf(';');
            if (semicolonIndex != -1) {
                subtype = subtype.substring(0, semicolonIndex);
            }
            return subtype.toLowerCase();
        }

        return "unknown";
    }

    /**
     * 根据MIME类型获取主类型（如"image/jpeg" → "image"）
     */
    public static String getPrimaryType(String mimeType) {
        if (mimeType == null || mimeType.isEmpty()) {
            return "unknown";
        }

        int slashIndex = mimeType.indexOf('/');
        if (slashIndex != -1) {
            return mimeType.substring(0, slashIndex).toLowerCase();
        }

        return "unknown";
    }
}