package com.lcsdl.ead.enrollment.services;

import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;

import com.lcsdl.ead.enrollment.dtos.EnrollmentRequestDTO;
import com.lcsdl.ead.enrollment.dtos.EnrollmentResponseDTO;
import com.lcsdl.ead.enrollment.dtos.StudentDTO;

public interface EnrollmentService {

	EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO enrollmentDto);

	List<EnrollmentResponseDTO> getCoursesByStudentId(UUID userId);

	List<StudentDTO> getStudentsByCourseId(UUID courseId);

}
