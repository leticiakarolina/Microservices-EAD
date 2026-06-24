package com.lcsdl.ead.enrollment.services;

import com.lcsdl.ead.enrollment.dtos.EnrollmentDTO;
import com.lcsdl.ead.enrollment.dtos.EnrollmentReadDTO;

public interface EnrollmentService {

	EnrollmentReadDTO createEnrollment(EnrollmentDTO enrollmentDto);

}
