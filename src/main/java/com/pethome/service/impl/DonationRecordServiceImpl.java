package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.DonationRecord;
import com.pethome.mapper.DonationRecordMapper;
import com.pethome.service.DonationRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储捐赠记录 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class DonationRecordServiceImpl extends ServiceImpl<DonationRecordMapper, DonationRecord> implements DonationRecordService {

}
