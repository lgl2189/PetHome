package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.RescueRecord;
import com.pethome.mapper.RescueRecordMapper;
import com.pethome.service.RescueRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储救助相关内容 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class RescueRecordServiceImpl extends ServiceImpl<RescueRecordMapper, RescueRecord> implements RescueRecordService {

    @Override
    public RescueRecord getRescueRecordByRescueId(Integer rescueId){
        return getById(rescueId);
    }

    @Override
    public RescueRecord getRescueRecordByAnimalId(Integer animalId) {
        LambdaQueryWrapper<RescueRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RescueRecord::getAnimalId, animalId);
        return getOne(queryWrapper);
    }

    @Override
    public RescueRecord addRescueRecord(RescueRecord rescueRecord) {
        boolean result = this.save(rescueRecord);
        if(!result){
            return null;
        }
        return rescueRecord;
    }

    @Override
    public boolean updateRescueRecord(RescueRecord rescueRecord) {
        rescueRecord.setAnimalId(null);
        return this.updateById(rescueRecord);
    }
}
