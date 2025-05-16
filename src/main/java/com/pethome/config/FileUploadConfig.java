package com.pethome.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 16 15:39
 */

@Getter
@Configuration
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadConfig {

    private final String fileUploadRootPath;
    private final String fileUploadImagePath;
    private final String fileUploadVideoPath;
    private final String fileUploadPdfPath;
    private final String fileUploadTxtPath;
    private final String fileUploadOtherPath;

    public FileUploadConfig(@Value("${file.upload.root-path}") String fileUploadRootPath,
                            @Value("${file.upload.image-folder:image}") String fileUploadImageFolder,
                            @Value("${file.upload.video-folder:video}") String fileUploadVideoFolder,
                            @Value("${file.upload.pdf-folder:pdf}") String fileUploadPdfFolder,
                            @Value("${file.upload.txt-folder:txt}") String fileUploadTxtFolder,
                            @Value("${file.upload.other-folder:other}") String fileUploadOtherFolder) {
        Assert.notNull(fileUploadRootPath, "fileUploadPath must not be null");
        Assert.notNull(fileUploadImageFolder, "fileUploadImageFolder must not be null");
        Assert.notNull(fileUploadVideoFolder, "fileUploadVideoFolder must not be null");
        Assert.notNull(fileUploadPdfFolder, "fileUploadPdfFolder must not be null");
        Assert.notNull(fileUploadTxtFolder, "fileUploadTxtFolder must not be null");
        Assert.notNull(fileUploadOtherFolder, "fileUploadOtherFolder must not be null");
        // 移除末尾的斜杠或反斜杠
        fileUploadRootPath = removeTrailingSlash(fileUploadRootPath);
        fileUploadImageFolder = removeTrailingSlash(fileUploadImageFolder);
        fileUploadImageFolder = removeTrailingSlash(fileUploadImageFolder);
        fileUploadImageFolder = removeTrailingSlash(fileUploadImageFolder);
        fileUploadImageFolder = removeTrailingSlash(fileUploadImageFolder);
        fileUploadImageFolder = removeTrailingSlash(fileUploadImageFolder);
        // 移除文件夹开头的斜杠
        fileUploadImageFolder = removeStartSlash(fileUploadImageFolder);
        fileUploadVideoFolder = removeStartSlash(fileUploadVideoFolder);
        fileUploadPdfFolder = removeStartSlash(fileUploadPdfFolder);
        fileUploadTxtFolder = removeStartSlash(fileUploadTxtFolder);
        fileUploadOtherFolder = removeStartSlash(fileUploadOtherFolder);
        this.fileUploadRootPath = fileUploadRootPath;
        this.fileUploadImagePath = Paths.get(fileUploadRootPath, fileUploadImageFolder).toString();
        this.fileUploadVideoPath = Paths.get(fileUploadRootPath, fileUploadVideoFolder).toString();
        this.fileUploadPdfPath = Paths.get(fileUploadRootPath, fileUploadPdfFolder).toString();
        this.fileUploadTxtPath = Paths.get(fileUploadRootPath, fileUploadTxtFolder).toString();
        this.fileUploadOtherPath = Paths.get(fileUploadRootPath, fileUploadOtherFolder).toString();

        // 确保目录存在
        createDirectoryIfNotExists(this.fileUploadImagePath);
        createDirectoryIfNotExists(this.fileUploadVideoPath);
        createDirectoryIfNotExists(this.fileUploadPdfPath);
        createDirectoryIfNotExists(this.fileUploadTxtPath);
        createDirectoryIfNotExists(this.fileUploadOtherPath);
    }

    public static String removeStartSlash(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }
        if(path.startsWith("/")) {
            path = path.substring(1);
        }
        return path;
    }

    public static String removeTrailingSlash(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }
        // 循环移除末尾的斜杠（处理多个连续斜杠的情况）
        while (path.endsWith("/") || path.endsWith("\\")) {
            path = path.substring(0, path.length() - 1);
        }

        return path;
    }

    // 创建目录的方法
    private void createDirectoryIfNotExists(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException("创建目录失败: " + path, e);
        }
    }

}