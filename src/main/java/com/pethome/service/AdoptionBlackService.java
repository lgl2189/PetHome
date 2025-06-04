package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.AdoptionBlack;

import java.util.List;

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
     * 根据用户id获取黑名单用户信息
     * @param userId 用户id
     * @return 黑名单用户信息
     */
    AdoptionBlack getBlackUser(Integer userId);

    /**
     * 根据站点id获取黑名单用户信息
     * @param stationId 站点id
     * @return 黑名单用户信息列表
     */
    List<AdoptionBlack> getBlackListByStationId(Integer stationId);

    /**
     * 向黑名单添加用户
     * @param adoptionBlack 待加入黑名单的用户
     * @return true表示添加成功，false表示添加失败
     */
    boolean addBlackUser(AdoptionBlack adoptionBlack);

    /**
     * 删除黑名单中的用户
     * @param userId 黑名单用户id
     * @return true表示删除成功，false表示删除失败
     */
    boolean deleteBlackUser(Integer userId);
    /**
     * 判断用户是否在领养黑名单中
     * @param userId 用户id
     * @return true表示在黑名单中，false表示不在黑名单中
     */
    boolean isBlackUser(Integer userId);
}
