package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lgl
 * @since 2025-05-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("file_record")
public class FileRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "file_id" ,type = IdType.AUTO)
    private Long fileId;

    private Long fileGroupId;

    private String url;
}
