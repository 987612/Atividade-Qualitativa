package com.backend.course.services.impl;

import com.backend.course.dtos.ModuleRecordDto;
import com.backend.course.models.CourseModel;
import com.backend.course.models.ModuleModel;
import com.backend.course.repositories.ModuleRepository;
import com.backend.course.services.ModuleServiceAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleServiceImpl implements ModuleServiceAPI {

    final ModuleRepository moduleRepository;

    public ModuleServiceImpl(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public ModuleModel save(ModuleRecordDto moduleRecordDto, CourseModel courseModel) {
        ModuleModel moduleModel = new ModuleModel();
        BeanUtils.copyProperties(moduleRecordDto, moduleModel);
        moduleModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        moduleModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        moduleModel.setCourse(courseModel);
        return moduleRepository.save(moduleModel);
    }

    @Override
    public List<ModuleModel> findAllModulesIntoCourse(Integer courseId) {
        return moduleRepository.findAllModulesIntoCourse(courseId);
    }

    @Override
    public Optional<ModuleModel> findModulesIntoCourse(Integer moduleId, Integer courseId) {
        Optional<ModuleModel> moduleIntoCourse = moduleRepository.findModulesIntoCourse(courseId, moduleId);
        if (moduleIntoCourse.isEmpty()) {
            throw new RuntimeException("Error: module not found for this course");
        }
        return moduleIntoCourse;
    }

    @Override
    public Optional<ModuleModel> findById(Integer moduleId) {
        Optional<ModuleModel> moduleModel = moduleRepository.findById(moduleId);
        if (moduleModel.isEmpty()) {
            throw new RuntimeException("Error: module not found");
        }
        return moduleModel;
    }

    @Override
    public ModuleModel update(ModuleRecordDto moduleRecordDto, ModuleModel moduleModel) {
        BeanUtils.copyProperties(moduleRecordDto, moduleModel);
        moduleModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return moduleRepository.save(moduleModel);
    }
}

