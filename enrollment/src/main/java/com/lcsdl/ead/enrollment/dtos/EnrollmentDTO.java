package com.lcsdl.ead.enrollment.dtos;

import java.util.UUID;

public record EnrollmentDTO(
	UUID courseId,
	UUID userId) {

}
