package com.backend.course.repositories;

import com.backend.course.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseModel, Integer> {
    boolean existsByName(String name);
}

