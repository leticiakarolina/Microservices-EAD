package com.lcsdl.ead.course.dtos;

import jakarta.validation.constraints.NotBlank;

public record LessonDTO(
	@NotBlank(message = "A title is required")
	String title,
	
	@NotBlank(message = "A description is required")
	String description,
	
	@NotBlank(message = "The video is required")
	String videoUrl) {

}
