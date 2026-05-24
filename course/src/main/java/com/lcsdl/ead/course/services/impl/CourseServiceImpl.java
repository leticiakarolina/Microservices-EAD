package com.lcsdl.ead.course.services.impl;

import org.springframework.stereotype.Service;

import com.lcsdl.ead.course.enums.repositories.CourseRepository;
import com.lcsdl.ead.course.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	
}
