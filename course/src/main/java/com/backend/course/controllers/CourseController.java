package com.backend.course.controllers;

import com.backend.course.dtos.CourseRecordDto;
import com.backend.course.models.CourseModel;
import com.backend.course.services.CourseServiceAPI;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    final CourseServiceAPI courseService;

    public CourseController(CourseServiceAPI courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCourse(
            @RequestBody @Valid CourseRecordDto courseRecordDto
    ) {
        if (courseService.existsByName(courseRecordDto.name())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: Course already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.save(courseRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<CourseModel>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(courseService.findAll());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getOneCourse(@PathVariable(value = "courseId") Integer courseId) {
        Optional<CourseModel> course = courseService.findById(courseId);
        if (course.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(course.get());
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(
            @PathVariable(value = "courseId") Integer courseId,
            @RequestBody @Valid CourseRecordDto courseRecordDto
    ) {
        Optional<CourseModel> courseOptional = courseService.findById(courseId);
        if (courseOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(courseService.update(courseRecordDto, courseOptional.get()));
    }
}
