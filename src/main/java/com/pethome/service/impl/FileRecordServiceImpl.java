package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.config.FileUploadConfig;
import com.pethome.constant.Constant;
import com.pethome.entity.mybatis.FileRecord;
import com.pethome.entity.wrapper.ResultWrapper;
import com.pethome.exception.DataBaseOperatorException;
import com.pethome.mapper.FileRecordMapper;
import com.pethome.service.FileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private final FileRecordMapper fileRecordMapper;

    @Autowired
    public FileRecordServiceImpl(FileUploadConfig fileUploadConfig,
                                 FileRecordMapper fileRecordMapper) {
        Assert.notNull(fileUploadConfig, "fileUploadConfig must not be null");
        Assert.notNull(fileRecordMapper, "fileRecordMapper must not be null");
        this.fileUploadConfig = fileUploadConfig;
        this.fileRecordMapper = fileRecordMapper;
    }

    @Override
    public ResultWrapper saveFileRecordGroup(MultipartFile[] fileArray) throws IOException {
        if (fileArray == null || fileArray.length == 0) {
            return new ResultWrapper(false, null, "上传文件不能为空");
        }
        List<String> savePathList = new ArrayList<>();
        for (MultipartFile file : fileArray) {
            if (file == null || file.isEmpty()) {
                continue;
            }
            savePathList.add(saveFile(file));
        }
        Long maxGid = fileRecordMapper.getMaxGroupId() + 1;
        List<String> failList = new ArrayList<>();
        for (String path : savePathList) {
            FileRecord fileRecord = new FileRecord();
            fileRecord.setFileGroupId(maxGid);
            fileRecord.setFileUrl(path);
            boolean result = save(fileRecord);
            if (!result) {
                failList.add(path);
            }
        }
        if (!failList.isEmpty()) {
            return new ResultWrapper(false, null, failList);
        }
        return new ResultWrapper(true, maxGid);
    }

    @Override
    public Long saveFileRecord(MultipartFile file) throws IOException, DataBaseOperatorException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String savePath = saveFile(file);
        FileRecord fileRecord = new FileRecord();
        Long maxGid = fileRecordMapper.getMaxGroupId() + 1;
        fileRecord.setFileGroupId(maxGid);
        fileRecord.setFileUrl(savePath);
        boolean result = save(fileRecord);
        if (!result) {
            throw new DataBaseOperatorException();
        }
        return fileRecord.getFileId();
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String saveFolderPath = getSaveFolderPath(file.getContentType(), fileName);
        String uuid = UUID.randomUUID().toString();
        String newFileName;
        if (!StringUtils.hasText(fileName) || fileName.lastIndexOf(".") == -1) {
            newFileName = uuid + "." + Constant.UNKNOWN_FILE_TYPE;
        }
        else {
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            newFileName = uuid + suffix;
        }
        String savePath = saveFolderPath + Constant.FILE_SEPARATOR + newFileName;
        try (FileOutputStream fos = new FileOutputStream(savePath)) {
            fos.write(file.getBytes());
        }
        catch (IOException e) {
            throw new IOException("上传文件写入失败:" + fileName + "\n" + e.getMessage());
        }
        return fileUploadConfig.getRelativePathFromFullPath(savePath);
    }


    /**
     * 获取保存路径
     *
     * @param contentType 上传文件的ContentType值
     * @param fileName    上传文件的文件名
     * @return 保存路径
     */
    public String getSaveFolderPath(String contentType, String fileName) {
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

    @Override
    public FileRecord getFileRecordByFileId(long fileId) {
        return fileRecordMapper.selectById(fileId);
    }

    @Override
    public List<FileRecord> getFileRecordByFileGroupId(long fileGroupId) {
        LambdaQueryWrapper<FileRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileRecord::getFileGroupId, fileGroupId);
        return fileRecordMapper.selectList(queryWrapper);
    }

    @Override
    public FileRecord getFileFullPath(FileRecord fileRecord) {
        fileRecord.setFileUrl(fileUploadConfig.getServerResourceBaseUrl() + fileRecord.getFileUrl());
        return fileRecord;
    }

    @Override
    public List<FileRecord> getFileGroupFullPath(List<FileRecord> fileRecordList) {
        fileRecordList.forEach(this::getFileFullPath);
        return fileRecordList;
    }
}
