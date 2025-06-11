package com.backend.course.dtos;

import jakarta.validation.constraints.NotBlank;

public record ModuleRecordDto(

        @NotBlank(message = "title is mandatory")
        String title,

        @NotBlank(message = "description is mandatory")
        String description

) {}
