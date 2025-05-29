package com.pethome.util;

import com.pethome.entity.mybatis.User;
import com.pethome.dto.UserDetail;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 5月 02 15:14
 */


public class UserUtil {

    /**
     * 去除UserDetail类中的敏感信息
     * @param userDetail 原始UserDetail对象
     * @return 去除敏感信息的UserDetail对象
     */
    public static UserDetail removeSensitiveInfo(UserDetail userDetail) {
        userDetail.setUserPassword(null);
        userDetail.setChinaId(null);
        userDetail.setRealName(null);
        return userDetail;
    }

    /**
     * 去除User类中的禁止显示信息
     * @param user 原始User对象
     * @return 去除禁止显示信息的User对象
     */
    public static User removeProhibitedInfo(User user) {
        User newUser = new User(user);
        newUser.setUserPassword(null);
        return newUser;
    }

    /**
     * 去除User类中的敏感信息
     * @param user 原始User对象
     * @return 去除敏感信息的User对象
     */
    public static User removeSensitiveInfo(User user) {
        User newUser = removeProhibitedInfo(user);
        newUser.setChinaId(null);
        newUser.setRealName(null);
        return newUser;
    }

    public static User getModifiableUser(User user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setBirthDate(user.getBirthDate());
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        return newUser;
    }


}