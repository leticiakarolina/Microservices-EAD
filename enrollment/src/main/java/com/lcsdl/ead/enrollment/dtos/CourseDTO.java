package com.lcsdl.ead.enrollment.dtos;

import java.util.UUID;

public record CourseDTO(
	String name,
	UUID courseId) {

}
