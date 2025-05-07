package com.pethome.entity.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pethome.entity.mybatis.Role;
import com.pethome.entity.mybatis.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 29 13:53
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer userId;

    // 因为getUsername方法被@JsonIgnore注解，所以Json序列化时会默认不序列化userName字段
    // 使用@JsonProperty注解来指定Json序列化时属性名称，来让jackson序列化userName字段
    @JsonProperty("userName")
    private String userName;

    private String userPassword;

    private String chinaId;

    private String realName;

    private LocalDate birthDate;

    private String phone;

    private String email;

    private List<Role> roleList;

    private LocalDateTime expireDateTime;

    public UserDetail(User user, List<Role> roleList) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userPassword = user.getUserPassword();
        this.chinaId = user.getChinaId();
        this.realName = user.getRealName();
        this.birthDate = user.getBirthDate();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.roleList = roleList;
        this.expireDateTime = LocalDateTime.now();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roleList == null || roleList.isEmpty()) {
            return Collections.emptyList();
        }
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleTag()))
                .collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return userPassword;
    }

    // 在Json序列化时，忽略掉这个方法，避免影响到userName，导致前端代码期望的属性名称为userName，而JSON序列化时属性名称为username
    @JsonIgnore
    @Override
    public String getUsername() {
        return userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}