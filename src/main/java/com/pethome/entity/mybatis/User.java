package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 存放用户信息
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPassword;

    private String chinaId;

    private String realName;

    private LocalDate birthDate;

    private String phone;

    private String email;

    public User(User user){
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userPassword = user.getUserPassword();
        this.chinaId = user.getChinaId();
        this.realName = user.getRealName();
        this.birthDate = user.getBirthDate();
        this.phone = user.getPhone();
        this.email = user.getEmail();
    }
}
