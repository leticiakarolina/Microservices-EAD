package com.lcsdl.ead.enrollment.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.lcsdl.ead.enrollment.dtos.EnrollmentDTO;
import com.lcsdl.ead.enrollment.enums.EnrollmentStatus;
import com.lcsdl.ead.enrollment.models.Enrollment;
import com.lcsdl.ead.enrollment.repositories.EnrollmentQueryRepository;
import com.lcsdl.ead.enrollment.repositories.EnrollmentRepository;
import com.lcsdl.ead.enrollment.services.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	private final EnrollmentRepository enrollmentRepository;
	private final EnrollmentQueryRepository enrollQueryRepository;
	
	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, EnrollmentQueryRepository enrollQueryRepository) {
		this.enrollmentRepository = enrollmentRepository;
		this.enrollQueryRepository = enrollQueryRepository;
	}

	@Override
	public Enrollment createEnrollment(EnrollmentDTO enrollmentDto) {
		Enrollment enrollment = new Enrollment();
		enrollment.setCourseId(enrollmentDto.courseId());
		enrollment.setUserId(enrollmentDto.userId());
		enrollment.setEnrolledAt(LocalDateTime.now(ZoneId.of("UTC")));
		enrollment.setStatus(EnrollmentStatus.ACTIVE);
		
		enrollmentRepository.save(enrollment);
		
		return null;
	}

}
