package com.pethome.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

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

    public FileUploadConfig(@Value("${file.upload.root-path}")String fileUploadRootPath,
                            @Value("${file.upload.image-folder:image}")String fileUploadImageFolder,
                            @Value("${file.upload.video-folder:video}")String fileUploadVideoFolder,
                            @Value("${file.upload.pdf-folder:pdf}")String fileUploadPdfFolder,
                            @Value("${file.upload.txt-folder:txt}")String fileUploadTxtFolder,
                            @Value("${file.upload.other-folder:other}")String fileUploadOtherFolder){
        Assert.notNull(fileUploadRootPath, "fileUploadPath must not be null");
        Assert.notNull(fileUploadImageFolder, "fileUploadImageFolder must not be null");
        Assert.notNull(fileUploadVideoFolder, "fileUploadVideoFolder must not be null");
        Assert.notNull(fileUploadPdfFolder, "fileUploadPdfFolder must not be null");
        Assert.notNull(fileUploadTxtFolder, "fileUploadTxtFolder must not be null");
        Assert.notNull(fileUploadOtherFolder, "fileUploadOtherFolder must not be null");
        this.fileUploadRootPath = fileUploadRootPath;
        if(!fileUploadImageFolder.startsWith("/")){
            fileUploadImageFolder = "/" + fileUploadImageFolder;
        }
        this.fileUploadImagePath = fileUploadRootPath + "/" + fileUploadImageFolder;
        if(!fileUploadVideoFolder.startsWith("/")){
            fileUploadVideoFolder = "/" + fileUploadVideoFolder;
        }
        this.fileUploadVideoPath = fileUploadRootPath + "/" + fileUploadVideoFolder;
        if(!fileUploadPdfFolder.startsWith("/")){
            fileUploadPdfFolder = "/" + fileUploadPdfFolder;
        }
        this.fileUploadPdfPath = fileUploadRootPath + "/" + fileUploadPdfFolder;
        if(!fileUploadTxtFolder.startsWith("/")){
            fileUploadTxtFolder = "/" + fileUploadTxtFolder;
        }
        this.fileUploadTxtPath = fileUploadRootPath + "/" + fileUploadTxtFolder;
        if(!fileUploadOtherFolder.startsWith("/")){
            fileUploadOtherFolder = "/" + fileUploadOtherFolder;
        }
        this.fileUploadOtherPath = fileUploadRootPath + "/" + fileUploadOtherFolder;
    }

}