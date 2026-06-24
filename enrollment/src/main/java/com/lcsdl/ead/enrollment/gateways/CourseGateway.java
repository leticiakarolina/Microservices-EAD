package com.lcsdl.ead.enrollment.gateways;

import java.util.UUID;

import com.lcsdl.ead.enrollment.dtos.CourseDTO;

public interface CourseGateway {

	CourseDTO findCourseById(UUID courseId);
}
