package com.lcsdl.ead.enrollment.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcsdl.ead.enrollment.dtos.CourseDTO;
import com.lcsdl.ead.enrollment.dtos.EnrollmentDTO;
import com.lcsdl.ead.enrollment.dtos.EnrollmentReadDTO;
import com.lcsdl.ead.enrollment.dtos.UserDTO;
import com.lcsdl.ead.enrollment.enums.EnrollmentStatus;
import com.lcsdl.ead.enrollment.enums.UserStatus;
import com.lcsdl.ead.enrollment.exception.BusinessRuleException;
import com.lcsdl.ead.enrollment.gateways.CourseGateway;
import com.lcsdl.ead.enrollment.gateways.UserGateway;
import com.lcsdl.ead.enrollment.models.Enrollment;
import com.lcsdl.ead.enrollment.models.EnrollmentQuery;
import com.lcsdl.ead.enrollment.repositories.EnrollmentQueryRepository;
import com.lcsdl.ead.enrollment.repositories.EnrollmentRepository;
import com.lcsdl.ead.enrollment.services.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	private final EnrollmentRepository enrollmentRepository;
	private final EnrollmentQueryRepository enrollQueryRepository;
	private final UserGateway userGateway;
	private final CourseGateway courseGateway;
	
	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, EnrollmentQueryRepository enrollQueryRepository, UserGateway userGateway,
			CourseGateway courseGateway) {
		this.enrollmentRepository = enrollmentRepository;
		this.enrollQueryRepository = enrollQueryRepository;
		this.userGateway = userGateway;
		this.courseGateway = courseGateway;
	}

	@Override
	@Transactional
	public EnrollmentReadDTO createEnrollment(EnrollmentDTO enrollmentDto) {
		UserDTO user = userGateway.findUserById(enrollmentDto.userId());
		
		if(user.status() == UserStatus.BLOCKED) {
			throw new BusinessRuleException("This user is not allowed to enroll in this course.");
		}
		
		CourseDTO course = courseGateway.findCourseById(enrollmentDto.courseId());

		if(enrollmentRepository.existsByUserIdAndCourseId(user.userId(), course.courseId())) {
			throw new BusinessRuleException("This enrollment already exists!");
		}
		
		Enrollment enrollmentEntity = createEnrollmentEntity(user, course);
		Enrollment enrollment = enrollmentRepository.save(enrollmentEntity);
		
		EnrollmentQuery enrollRead = createEnrollmentReadEntity(user, course, enrollment);
		enrollQueryRepository.save(enrollRead);
		
		return createEnrollmentReadDTO(enrollRead);
	}
	
	private Enrollment createEnrollmentEntity(UserDTO user, CourseDTO course) {
		Enrollment enrollment = new Enrollment();
		enrollment.setCourseId(course.courseId());
		enrollment.setUserId(user.userId());
		enrollment.setEnrolledAt(LocalDateTime.now(ZoneId.of("UTC")));
		enrollment.setStatus(EnrollmentStatus.ACTIVE);
		
		return enrollment;
	}
	
	private EnrollmentQuery createEnrollmentReadEntity(UserDTO user, CourseDTO course, Enrollment enrollment) {
		EnrollmentQuery enrollRead = new EnrollmentQuery();
		enrollRead.setCourseId(course.courseId());
		enrollRead.setCourseName(course.name());
		enrollRead.setUserId(user.userId());
		enrollRead.setUserFullName(user.fullName());
		enrollRead.setEnrolledAt(enrollment.getEnrolledAt());
		enrollRead.setStatus(enrollment.getStatus());
		enrollRead.setEnrollmentId(enrollment.getEnrollmentId());
		
		return enrollRead;
	}
	
	private EnrollmentReadDTO createEnrollmentReadDTO(EnrollmentQuery enrollment) {
		EnrollmentReadDTO enrollDTO = new EnrollmentReadDTO(
			enrollment.getCourseName(), 
			enrollment.getUserFullName(), 
			enrollment.getEnrolledAt(), 
			enrollment.getStatus(), 
			enrollment.getEnrollmentId());
		
		return enrollDTO;
	}

}
