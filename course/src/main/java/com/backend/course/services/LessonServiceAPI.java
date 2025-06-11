package com.backend.course.services;

import com.backend.course.models.LessonModel;

import java.util.List;
import java.util.Optional;

public interface LessonServiceAPI {
    LessonModel save(LessonModel lessonModel);
    List<LessonModel> findAll();
    Optional<LessonModel> findById(Long id);
    void delete(LessonModel lessonModel);
}
