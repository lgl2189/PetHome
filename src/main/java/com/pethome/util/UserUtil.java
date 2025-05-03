package com.pethome.util;

import com.pethome.entity.web.UserDetail;

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

}