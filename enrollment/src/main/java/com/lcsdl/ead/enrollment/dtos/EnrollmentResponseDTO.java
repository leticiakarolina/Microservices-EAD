package com.lcsdl.ead.enrollment.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.lcsdl.ead.enrollment.enums.EnrollmentStatus;

public record EnrollmentResponseDTO(
	String courseName,
	String userName,
	LocalDateTime enrolledAt,
	EnrollmentStatus status,
	UUID enrollmentId) {

}
