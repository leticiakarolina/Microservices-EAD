package com.lcsdl.ead.course.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lcsdl.ead.course.dtos.CourseDTO;
import com.lcsdl.ead.course.exceptions.NotFoundException;
import com.lcsdl.ead.course.models.Course;
import com.lcsdl.ead.course.models.Module;
import com.lcsdl.ead.course.repositories.CourseRepository;
import com.lcsdl.ead.course.services.CourseService;
import com.lcsdl.ead.course.services.ModuleService;
import com.lcsdl.ead.course.specifications.CourseSpecifications;
import com.lcsdl.ead.course.specifications.filters.CourseSearchFilter;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final ModuleService moduleService;

	public CourseServiceImpl(CourseRepository courseRepository, ModuleService moduleService) {
		this.courseRepository = courseRepository;
		this.moduleService = moduleService;
	}

	@Override
	public void deleteCourse(UUID courseId) {
		Course course = getCourseById(courseId).get();
		List<Module> modules = moduleService.findAllModulesByCourse(course.getCourseId());
		
		if(!modules.isEmpty()) {
			for(Module module : modules) {
				moduleService.deleteModule(module);
			}
		}
		courseRepository.delete(course);
	}

	@Override
	public Course saveCourse(CourseDTO courseDto) {
		Course course = new Course();
		BeanUtils.copyProperties(courseDto, course);
		course.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		course.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		return courseRepository.save(course);
	}

	@Override
	public boolean existsByName(String name) {
		return courseRepository.existsByName(name);
	}

	@Override
	public Page<Course> getCourses(Pageable pageable, CourseSearchFilter courseFilter) {
		Specification<Course> spec = Specification.where(CourseSpecifications.courseLevel(courseFilter.courseLevel()))
				.and(CourseSpecifications.likeName(courseFilter.name()));
			
		return courseRepository.findAll(spec, pageable);
	}

	@Override
	public Optional<Course> getCourseById(UUID courseId) {
		Optional<Course> course = courseRepository.findById(courseId);
		
		if(course.isEmpty()) {
			throw new NotFoundException("The course provided with the id " + courseId + " was not found.");
		}
		
		return course;
	}

	@Override
	public Course updateCourse(CourseDTO courseDto, UUID courseId) {
		Course course = getCourseById(courseId).get();
		
		BeanUtils.copyProperties(courseDto, course);
		course.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		return courseRepository.save(course);
	}
	
}
