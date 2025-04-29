package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 存储角色-权限关系
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "role_permission_id", type = IdType.AUTO)
    private Integer rolePermissionId;

    private Integer roleId;

    private Integer permissionId;

}
