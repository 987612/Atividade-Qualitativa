package com.backend.course.services.impl;

import com.backend.course.dtos.CourseRecordDto;
import com.backend.course.models.CourseModel;
import com.backend.course.repositories.CourseRepository;
import com.backend.course.repositories.ModuleRepository;
import com.backend.course.services.CourseServiceAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseServiceAPI {

    final CourseRepository courseRepository;
    final ModuleRepository moduleRepository;

    public CourseServiceImpl(CourseRepository courseRepository, ModuleRepository moduleRepository) {
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    public CourseModel save(CourseRecordDto courseRecordDto) {
        var courseModel = new CourseModel();
        BeanUtils.copyProperties(courseRecordDto, courseModel);
        courseModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return courseRepository.save(courseModel);
    }

    @Override
    public boolean existsByName(String name) {
        return courseRepository.existsByName(name);
    }

    @Override
    public List<CourseModel> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<CourseModel> findById(Integer courseId) {
        Optional<CourseModel> courseModelOptional = courseRepository.findById(courseId);
        if (!courseModelOptional.isPresent()) {
            throw new RuntimeException("Error: course not found");
        }
        return courseModelOptional;
    }

    @Override
    public CourseModel update(CourseRecordDto courseRecordDto, CourseModel courseModel) {
        BeanUtils.copyProperties(courseRecordDto, courseModel);
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return courseRepository.save(courseModel);
    }
}
