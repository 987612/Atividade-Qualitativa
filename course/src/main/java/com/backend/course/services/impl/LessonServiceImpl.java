package com.backend.course.services.impl;

import com.backend.course.models.LessonModel;
import com.backend.course.repositories.LessonRepository;
import com.backend.course.services.LessonServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonServiceAPI {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public LessonModel save(LessonModel lessonModel) {
        return lessonRepository.save(lessonModel);
    }

    @Override
    public List<LessonModel> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Optional<LessonModel> findById(Long id) {
        return lessonRepository.findById(id);
    }

    @Override
    public void delete(LessonModel lessonModel) {
        lessonRepository.delete(lessonModel);
    }
}
