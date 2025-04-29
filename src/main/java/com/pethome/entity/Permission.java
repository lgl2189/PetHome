package com.pethome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 存储权限信息
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    private String permissionName;

    private String permissionCode;

    private String permissionUrl;

    private Integer parentId;
}
