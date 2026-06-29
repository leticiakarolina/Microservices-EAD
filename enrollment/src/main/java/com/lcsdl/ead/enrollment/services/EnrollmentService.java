package com.lcsdl.ead.enrollment.services;

import java.util.List;
import java.util.UUID;

import com.lcsdl.ead.enrollment.dtos.EnrollmentDTO;
import com.lcsdl.ead.enrollment.dtos.EnrollmentReadDTO;

public interface EnrollmentService {

	EnrollmentReadDTO createEnrollment(EnrollmentDTO enrollmentDto);

	List<EnrollmentReadDTO> getCoursesByUserId(UUID userId);

}
