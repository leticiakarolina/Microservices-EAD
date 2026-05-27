package com.lcsdl.ead.course.dtos;

import java.util.UUID;

import com.lcsdl.ead.course.enums.CourseLevel;
import com.lcsdl.ead.course.enums.CourseStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
	@NotBlank
	String name,
	
	@NotBlank
	String description,
	
	@NotNull
	CourseStatus courseStatus,
	
	@NotNull
	CourseLevel courseLevel,
	
	@NotNull
	UUID userInstructor,
	String imageUrl) {

}
