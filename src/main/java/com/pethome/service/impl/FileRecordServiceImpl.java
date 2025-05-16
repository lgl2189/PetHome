package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.config.FileUploadConfig;
import com.pethome.entity.mybatis.FileRecord;
import com.pethome.mapper.FileRecordMapper;
import com.pethome.service.FileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-05-16
 */
@Service
public class FileRecordServiceImpl extends ServiceImpl<FileRecordMapper, FileRecord> implements FileRecordService {

    private final FileUploadConfig fileUploadConfig;

    @Autowired
    public FileRecordServiceImpl(FileUploadConfig fileUploadConfig) {
        Assert.notNull(fileUploadConfig, "fileUploadConfig must not be null");
        this.fileUploadConfig = fileUploadConfig;
    }

    /**
     * 保存文件
     *
     * @param file 上传的文件对象
     * @return 文件路径
     */
    @Override
    public String saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String saveFolderPath = getSaveFolderPath(file.getContentType(), fileName);
        String uuid = UUID.randomUUID().toString();
        String newFileName;
        if (!StringUtils.hasText(fileName) || fileName.lastIndexOf(".") == -1) {
            newFileName = uuid + "." + file.getOriginalFilename();
        }
        else {
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            newFileName = uuid + "." + suffix;
        }
        String savePath = saveFolderPath + File.separator + newFileName;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            outputStream.write(file.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException("文件写入失败:" + fileName + "\n" + e.getMessage());
        }
        return savePath;
    }

    /**
     * 获取保存路径
     *
     * @param contentType 上传文件的ContentType值
     * @param fileName    上传文件的文件名
     * @return 保存路径
     */
    private String getSaveFolderPath(String contentType, String fileName) {
        if (StringUtils.hasText(contentType)) {
            if (contentType.startsWith("image/")) {
                return fileUploadConfig.getFileUploadImagePath();
            }
            if (contentType.startsWith("video/")) {
                return fileUploadConfig.getFileUploadVideoPath();
            }
        }
        if (StringUtils.hasText(fileName)) {
            if (fileName.endsWith(".pdf")) {
                return fileUploadConfig.getFileUploadPdfPath();
            }
            if (fileName.endsWith(".txt")) {
                return fileUploadConfig.getFileUploadTxtPath();
            }
        }
        return fileUploadConfig.getFileUploadOtherPath();
    }
}
