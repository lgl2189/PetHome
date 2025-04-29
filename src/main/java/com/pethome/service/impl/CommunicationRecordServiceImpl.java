package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.CommunicationRecord;
import com.pethome.mapper.CommunicationRecordMapper;
import com.pethome.service.CommunicationRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储用户间交流记录 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class CommunicationRecordServiceImpl extends ServiceImpl<CommunicationRecordMapper, CommunicationRecord> implements CommunicationRecordService {

}
