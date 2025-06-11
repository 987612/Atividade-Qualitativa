package com.backend.course.services;

import com.backend.course.dtos.CourseRecordDto;
import com.backend.course.models.CourseModel;

import java.util.List;
import java.util.Optional;

public interface CourseServiceAPI {
    CourseModel save(CourseRecordDto courseRecordDto);
    boolean existsByName(String name);
    List<CourseModel> findAll();
    Optional<CourseModel> findById(Integer courseId);
    CourseModel update(CourseRecordDto courseRecordDto, CourseModel courseModel);
}

