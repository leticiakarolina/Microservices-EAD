package com.lcsdl.ead.enrollment.dtos;

import java.util.UUID;

public record EnrollmentRequestDTO(
	UUID courseId,
	UUID userId) {

}
