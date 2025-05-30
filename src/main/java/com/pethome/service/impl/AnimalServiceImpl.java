package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.pethome.dto.receiver.AnimalReceiver;
import com.pethome.entity.enums.AnimalHealthStatusEnum;
import com.pethome.entity.mybatis.Animal;
import com.pethome.entity.wrapper.ResultWrapper;
import com.pethome.exception.DataBaseOperatorException;
import com.pethome.mapper.AnimalMapper;
import com.pethome.service.AnimalService;
import com.pethome.service.FileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 存储动物信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements AnimalService {

    private final FileRecordService fileRecordService;
    private final AnimalMapper animalMapper;

    @Autowired
    public AnimalServiceImpl(FileRecordService fileRecordService, AnimalMapper animalMapper) {
        Assert.notNull(fileRecordService, "fileRecordService must not be null");
        Assert.notNull(animalMapper, "animalMapper must not be null");
        this.fileRecordService = fileRecordService;
        this.animalMapper = animalMapper;
    }

    @Override
    public Animal saveAnimalInfo(AnimalReceiver animalReceiver) throws IOException, DataBaseOperatorException {
        Animal animal = new Animal();
        animal.setSpecies(animalReceiver.getSpecies());
        animal.setBreed(animalReceiver.getBreed());
        animal.setGender(animalReceiver.getGender());
        animal.setAge(animalReceiver.getAge());
        animal.setHealthStatus(AnimalHealthStatusEnum.fromValue(animalReceiver.getHealthStatus()));
        animal.setIsSterilized(animalReceiver.getIsSterilized());
        animal.setDescription(animalReceiver.getDescription());
        animal.setPersonality(animalReceiver.getPersonality());
        animal.setAbnormalWarning(animal.getHealthStatus() == AnimalHealthStatusEnum.ILL ||
                animal.getHealthStatus() == AnimalHealthStatusEnum.CRITICAL);
        animal.setRescueStationId(animalReceiver.getRescueStationId());
        // 保存上传文件
        if (animalReceiver.getImageArray() != null) {
            ResultWrapper imageResult = fileRecordService.saveFileRecordGroup(animalReceiver.getImageArray());
            animal.setImgGid((Long) imageResult.getResult());
        }
        if (animalReceiver.getVideoArray() != null) {
            ResultWrapper videoResult = fileRecordService.saveFileRecordGroup(animalReceiver.getVideoArray());
            animal.setVideoGid((Long) videoResult.getResult());
        }
        if (animalReceiver.getVaccinationRecordArray() != null) {
            ResultWrapper vaccinationResult = fileRecordService.saveFileRecordGroup(animalReceiver.getVaccinationRecordArray());
            animal.setVaccinationRecordGid((Long) vaccinationResult.getResult());
        }
        if (animalReceiver.getDewormingRecordArray() != null) {
            ResultWrapper dewormingResult = fileRecordService.saveFileRecordGroup(animalReceiver.getDewormingRecordArray());
            animal.setDewormingRecordGid((Long) dewormingResult.getResult());
        }
        if (animalReceiver.getMedicalReportArray() != null) {
            ResultWrapper medicalResult = fileRecordService.saveFileRecordGroup(animalReceiver.getMedicalReportArray());
            animal.setMedicalReportGid((Long) medicalResult.getResult());
        }
        // 保存动物信息
        save(animal);
        return animal;
    }

    @Override
    public List<Animal> getAnimalListRecommended(int num) {
        return animalMapper.selectRandomAnimalList(num);
    }

    @Override
    public List<Animal> getAnimalListWaitAdoptRecommended(int num){
        return animalMapper.selectRandomAnimalListWaitAdopt(num);
    }

    @Override
    public PageInfo<Animal> searchAnimalInfo(List<String> keyList, int pageNum, int pageSize) {
        List<Animal> animalList = animalMapper.selectAnimalByKeyList(keyList, pageNum, pageSize);
        return new PageInfo<>(animalList);
    }

    @Override
    public Animal getAnimalInfoById(int animalId) {
        return animalMapper.selectById(animalId);
    }

    @Override
    public PageInfo<Animal> searchAnimalInfoWaitAdopt(List<String> keyList, int pageNum, int pageSize) {
        List<Animal> animalList = animalMapper.selectAnimalWaitAdoptByKeyList(keyList, pageNum, pageSize);
        return new PageInfo<>(animalList);
    }

}
