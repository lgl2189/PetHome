package com.pethome.entity.web;

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

    private String userName;

    private String userPassword;

    private String chinaId;

    private String realName;

    private LocalDate birthDate;

    private String phone;

    private String email;

    private List<Role> roleList;

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
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roleList == null || roleList.isEmpty()) {
            return Collections.emptyList();
        }
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleTag()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}