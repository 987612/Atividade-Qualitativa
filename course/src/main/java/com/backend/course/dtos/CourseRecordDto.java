package com.backend.course.dtos;

import com.backend.course.enums.CourseLevel;
import com.backend.course.enums.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRecordDto(
        @NotBlank(message = "name is mandatory")
        String name,

        @NotNull(message = "courseStatus is mandatory")
        CourseStatus courseStatus,

        @NotNull(message = "courseLevel is mandatory")
        CourseLevel courseLevel,

        @NotNull(message = "instructorId is mandatory")
        Integer instructorId,

        @NotBlank
        String description,

        String imageUrl
) {

}