package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 存储志愿者任务记录
 * </p>
 *
 * @author lgl
 * @since 2025-06-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerTaskRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    private Integer taskId;

    private Integer userId;

    private Boolean isSignIn;

    private Long taskProveGid;
}
