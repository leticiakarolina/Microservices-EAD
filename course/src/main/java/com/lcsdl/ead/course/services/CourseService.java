package com.lcsdl.ead.course.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lcsdl.ead.course.dtos.CourseDTO;
import com.lcsdl.ead.course.models.Course;

public interface CourseService {

	void deleteCourse(UUID courseId);
	
	Course saveCourse(CourseDTO courseDto);

	boolean existsByName(String name);

	Page<Course> getCourses(Pageable pageable);

	Optional<Course> getCourseById(UUID courseId);

	Course updateCourse(CourseDTO courseDto, UUID courseId);
}
