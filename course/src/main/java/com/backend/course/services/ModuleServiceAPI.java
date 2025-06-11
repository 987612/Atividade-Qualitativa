package com.backend.course.services;

import com.backend.course.dtos.ModuleRecordDto;
import com.backend.course.models.CourseModel;
import com.backend.course.models.ModuleModel;

import java.util.List;
import java.util.Optional;

public interface ModuleServiceAPI {
    ModuleModel save(ModuleRecordDto moduleRecordDto, CourseModel courseModel);

    List<ModuleModel> findAllModulesIntoCourse(Integer courseId);

    Optional<ModuleModel> findModulesIntoCourse(Integer moduleId, Integer courseId);

    ModuleModel update(ModuleRecordDto moduleRecordDto, ModuleModel moduleModel);

    Optional<ModuleModel> findById(Integer moduleId);
}

