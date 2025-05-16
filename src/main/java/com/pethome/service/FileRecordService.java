package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.FileRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件相关Service接口
 */
public interface FileRecordService extends IService<FileRecord> {
    /**
     * 保存文件
     * @param file 上传的文件对象
     * @return 文件路径
     */
    String saveFile(MultipartFile file) throws IOException;
}
