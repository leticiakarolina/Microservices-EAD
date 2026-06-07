package com.lcsdl.ead.course.dtos;

import jakarta.validation.constraints.NotBlank;

public record ModuleDTO(
	@NotBlank(message = "A title is required")
	String title,
	
	@NotBlank(message = "A description is required")
	String description) {

}
