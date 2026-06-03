package com.lcsdl.ead.course.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcsdl.ead.course.dtos.ModuleDTO;
import com.lcsdl.ead.course.models.Course;
import com.lcsdl.ead.course.models.Lesson;
import com.lcsdl.ead.course.models.Module;
import com.lcsdl.ead.course.repositories.CourseRepository;
import com.lcsdl.ead.course.repositories.ModuleRepository;
import com.lcsdl.ead.course.services.LessonService;
import com.lcsdl.ead.course.services.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	private final ModuleRepository moduleRepository;
	private final LessonService lessonService;
	private final CourseRepository courseRepository;

	public ModuleServiceImpl(ModuleRepository moduleRepository, LessonService lessonService, CourseRepository courseRepository) {
		this.moduleRepository = moduleRepository;
		this.lessonService = lessonService;
		this.courseRepository = courseRepository;
	}

	@Override
	public void deleteModule(Module module) {
		List<Lesson> lessons = lessonService.findAllLessonByModuleId(module.getModuleId());
		
		if(!lessons.isEmpty()) {
			lessonService.deleteLessons(lessons);
		}
		
		moduleRepository.delete(module);
	}

	@Override
	public List<Module> findAllModulesByCourse(UUID courseId) {
		return moduleRepository.findAllByCourse_CourseId(courseId);
	}

	@Override
	@Transactional
	public Module saveModule(UUID courseId, ModuleDTO moduleDto) {
		Course course = findCourseById(courseId).get();
		Module module = createModule(moduleDto, course);
		return module;
	}
	
	private Module createModule(ModuleDTO moduleDto, Course course) {
		Module module = new Module();
		BeanUtils.copyProperties(moduleDto, module);
		module.setCourse(course);
		module.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		return moduleRepository.save(module);
	}

	@Override
	public Optional<Module> findOneModuleByCourse(UUID courseId, UUID moduleId) {
		findCourseById(courseId);
		Optional<Module> module = moduleRepository.findByModuleIdAndCourse_CourseId(moduleId, courseId);
		
		if(module.isEmpty()) {
			//
		}
		
		return module;
	}

	@Override
	public Module updateModule(UUID courseId, UUID moduleId, ModuleDTO moduleDto) {
		Module module = findOneModuleByCourse(courseId, moduleId).get();
		BeanUtils.copyProperties(moduleDto, module);
		return moduleRepository.save(module);
	}

	@Override
	public Optional<Module> getModuleById(UUID moduleId) {
		Optional<Module> module = moduleRepository.findById(moduleId);
		
		if(module.isEmpty()){
			//
		}
		
		return module;
	}
	
	private Optional<Course> findCourseById(UUID courseId){
		Optional<Course> course = courseRepository.findById(courseId);
		
		if(course.isEmpty()) {
			
		}
		
		return course;
	}

}
