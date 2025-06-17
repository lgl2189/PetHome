package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.config.FileUploadConfig;
import com.pethome.dto.sender.RescueStationInfo;
import com.pethome.entity.mybatis.RescueStation;
import com.pethome.entity.mybatis.User;
import com.pethome.mapper.RescueStationMapper;
import com.pethome.service.FileRecordService;
import com.pethome.service.RescueStationService;
import com.pethome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 存储救助站信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class RescueStationServiceImpl extends ServiceImpl<RescueStationMapper, RescueStation> implements RescueStationService {

    private final UserService userService;
    private final FileUploadConfig fileUploadConfig;
    private final FileRecordService fileRecordService;

    @Autowired
    public RescueStationServiceImpl(UserService userService,
                                    FileUploadConfig fileUploadConfig,
                                    FileRecordService fileRecordService) {
        Assert.notNull(userService, "userService must not be null");
        Assert.notNull(fileUploadConfig, "fileUploadConfig must not be null");
        Assert.notNull(fileRecordService, "fileRecordService must not be null");
        this.userService = userService;
        this.fileUploadConfig = fileUploadConfig;
        this.fileRecordService = fileRecordService;
    }

    @Override
    public List<RescueStation> getPublicInfoList() {
        LambdaQueryWrapper<RescueStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(RescueStation::getRescueStationId, RescueStation::getRescueStationName);
        return list(queryWrapper);
    }

    @Override
    public List<RescueStation> getStationListByAdminId(Integer adminId) {
        LambdaQueryWrapper<RescueStation> query = new LambdaQueryWrapper<>();
        query.eq(RescueStation::getAdminUserId, adminId);
        return list(query);
    }

    @Override
    public RescueStationInfo getRescueStationById(Integer rescueStationId) {
        RescueStation rescueStation = getById(rescueStationId);
        User user = userService.getPublicInfoById(rescueStation.getAdminUserId());
        RescueStationInfo rescueStationInfo = new RescueStationInfo(rescueStation, user);
        if (rescueStation.getPaymentQrcodeGid() != null) {
            List<String> paymentQrcodeUrlList = fileRecordService.getFileRecordByFileGroupId(rescueStation.getPaymentQrcodeGid())
                    .stream()
                    .map(fileRecord -> fileUploadConfig.getServerResourceBaseUrl() + fileRecord.getFileUrl())
                    .collect(Collectors.toList());
            rescueStationInfo.setPaymentQrcodeUrlList(paymentQrcodeUrlList);
        }
        return rescueStationInfo;
    }

    @Override
    public boolean updatePaymentQrcodeList(Integer rescueStationId, MultipartFile[] paymentQrcodeFileArray) throws IOException {
        Long fileGroupId = (Long) fileRecordService.saveFileRecordGroup(paymentQrcodeFileArray).getResult();
        RescueStation rescueStation = new RescueStation();
        rescueStation.setRescueStationId(rescueStationId);
        rescueStation.setPaymentQrcodeGid(fileGroupId);
        return this.updateById(rescueStation);
    }
}