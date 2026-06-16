package com.lcsdl.ead.enrollment.services;

import com.lcsdl.ead.enrollment.dtos.EnrollmentDTO;
import com.lcsdl.ead.enrollment.models.Enrollment;

public interface EnrollmentService {

	Enrollment createEnrollment(EnrollmentDTO enrollmentDto);

}
