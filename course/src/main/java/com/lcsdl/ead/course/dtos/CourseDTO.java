package com.lcsdl.ead.course.dtos;

import java.util.UUID;

import com.lcsdl.ead.course.enums.CourseLevel;
import com.lcsdl.ead.course.enums.CourseStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
	@NotBlank(message = "A name is required")
	String name,
	
	@NotBlank(message = "A description is required")
	String description,
	
	@NotNull(message = "The course status is required")
	CourseStatus courseStatus,
	
	@NotNull(message = "The course level is required")
	CourseLevel courseLevel,
	
	@NotNull(message = "The instructor is required")
	UUID userInstructor,
	
	String imageUrl) {

}
