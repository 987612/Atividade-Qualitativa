package com.backend.course.controllers;

import com.backend.course.dtos.LessonRecordDto;
import com.backend.course.models.LessonModel;
import com.backend.course.models.ModuleModel;
import com.backend.course.services.LessonServiceAPI;
import com.backend.course.services.ModuleServiceAPI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    LessonServiceAPI lessonService;

    @Autowired
    ModuleServiceAPI moduleService;

    @PostMapping
    public ResponseEntity<Object> saveLesson(@RequestBody @Valid LessonRecordDto lessonDto) {
        Optional<ModuleModel> moduleOpt = moduleService.findById(lessonDto.moduleId());
        if (moduleOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found.");
        }

        LessonModel lesson = new LessonModel();
        lesson.setTitle(lessonDto.title());
        lesson.setDescription(lessonDto.description());
        lesson.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        lesson.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        lesson.setModule(moduleOpt.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.save(lesson));
    }

    @GetMapping
    public ResponseEntity<List<LessonModel>> getAllLessons() {
        return ResponseEntity.ok(lessonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneLesson(@PathVariable Long id) {
        Optional<LessonModel> lessonOpt = lessonService.findById(id);
        if (lessonOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found.");
        }
        return ResponseEntity.ok(lessonOpt.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLesson(@PathVariable Long id,
                                               @RequestBody @Valid LessonRecordDto lessonDto) {
        Optional<LessonModel> lessonOpt = lessonService.findById(id);
        if (lessonOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found.");
        }

        Optional<ModuleModel> moduleOpt = moduleService.findById(lessonDto.moduleId());
        if (moduleOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found.");
        }

        LessonModel lesson = lessonOpt.get();
        lesson.setTitle(lessonDto.title());
        lesson.setDescription(lessonDto.description());
        lesson.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        lesson.setModule(moduleOpt.get());

        return ResponseEntity.ok(lessonService.save(lesson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLesson(@PathVariable Long id) {
        Optional<LessonModel> lessonOpt = lessonService.findById(id);
        if (lessonOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found.");
        }
        lessonService.delete(lessonOpt.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
