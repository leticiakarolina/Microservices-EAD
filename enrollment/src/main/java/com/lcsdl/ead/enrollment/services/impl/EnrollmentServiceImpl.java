package com.lcsdl.ead.enrollment.services.impl;

import org.springframework.stereotype.Service;

import com.lcsdl.ead.enrollment.dtos.CourseDTO;
import com.lcsdl.ead.enrollment.dtos.EnrollmentDTO;
import com.lcsdl.ead.enrollment.dtos.UserDTO;
import com.lcsdl.ead.enrollment.enums.UserStatus;
import com.lcsdl.ead.enrollment.gateways.CourseGateway;
import com.lcsdl.ead.enrollment.gateways.UserGateway;
import com.lcsdl.ead.enrollment.models.Enrollment;
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
	public Enrollment createEnrollment(EnrollmentDTO enrollmentDto) {
		UserDTO user = userGateway.findUserById(enrollmentDto.userId());
		
		if(user.status() == UserStatus.BLOCKED) {
			throw new RuntimeException("This user is not allowed to enroll this course");
		}
		
		CourseDTO course = courseGateway.findCourseById(enrollmentDto.courseId());
		
		System.out.println("debuggerrr" + course.name());
		
//		Enrollment enrollment = new Enrollment();
//		enrollment.setCourseId(enrollmentDto.courseId());
//		enrollment.setUserId(enrollmentDto.userId());
//		enrollment.setEnrolledAt(LocalDateTime.now(ZoneId.of("UTC")));
//		enrollment.setStatus(EnrollmentStatus.ACTIVE);
//		
//		enrollmentRepository.save(enrollment);
		
		return null;
	}

}
