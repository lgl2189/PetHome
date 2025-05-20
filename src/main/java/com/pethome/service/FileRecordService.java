package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.FileRecord;
import com.pethome.entity.wrapper.ResultWrapper;
import com.pethome.exception.DataBaseOperatorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件相关Service接口
 */
public interface FileRecordService extends IService<FileRecord> {
    /**
     * 插入文件组记录
     *
     * @param fileArray 上传的文件数组
     * @return 返回一个ResultWrapper对象，保存成功时，result中为文件组id，失败时，result为null，extraInfo中为出错的文件
     * @throws IOException               IO异常
     * @throws DataBaseOperatorException 数据库操作异常
     */
    ResultWrapper saveFileRecordGroup(MultipartFile[] fileArray) throws IOException;

    /**
     * 插入文件记录
     *
     * @param file 上传的文件对象
     * @return 文件Id
     * @throws IOException               IO异常
     * @throws DataBaseOperatorException 数据库操作异常
     */
    Long saveFileRecord(MultipartFile file) throws IOException, DataBaseOperatorException;

    /**
     * 保存文件到本地
     * @param file 文件对象
     * @return 文件路径
     * @throws IOException IO异常
     */
    String saveFile(MultipartFile file)throws IOException;
}
