package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.AdoptionBlack;
import org.apache.el.parser.BooleanNode;

/**
 * <p>
 * 存储领养黑名单 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface AdoptionBlackService extends IService<AdoptionBlack> {
    /**
     * 判断用户是否在领养黑名单中
     * @param userId 用户id
     * @return true表示在黑名单中，false表示不在黑名单中
     */
    boolean isBlackUser(Integer userId);
}
