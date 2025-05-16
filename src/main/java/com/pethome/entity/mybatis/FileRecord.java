package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lgl
 * @since 2025-05-16
 */
@TableName("file_record")
public class FileRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("file_id")
    private Long fileId;

    private String url;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FileRecord{" +
        "fileId = " + fileId +
        ", url = " + url +
        "}";
    }
}
