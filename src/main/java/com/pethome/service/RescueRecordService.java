package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.RescueRecord;

/**
 * <p>
 * 存储救助相关内容 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface RescueRecordService extends IService<RescueRecord> {

    RescueRecord getRescueRecordByRescueId(Integer rescueId);

    /**
     * 添加救助记录
     *
     * @param rescueRecord 待添加的救助记录
     * @return 添加后的救助记录，添加失败返回null
     */
    RescueRecord addRescueRecord(RescueRecord rescueRecord);

    /**
     * 更新救助记录
     * @param rescueRecord 更新的的救助记录内容
     * @return 更新成功返回true，更新失败返回false
     */
    boolean updateRescueRecord(RescueRecord rescueRecord);
}
