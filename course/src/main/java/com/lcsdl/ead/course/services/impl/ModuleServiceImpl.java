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
import com.lcsdl.ead.course.repositories.ModuleRepository;
import com.lcsdl.ead.course.services.LessonService;
import com.lcsdl.ead.course.services.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	private final ModuleRepository moduleRepository;
	private final LessonService lessonService;

	public ModuleServiceImpl(ModuleRepository moduleRepository, LessonService lessonService) {
		this.moduleRepository = moduleRepository;
		this.lessonService = lessonService;
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
	public Module saveModule(Course course, ModuleDTO moduleDto) {
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
		Optional<Module> module = moduleRepository.findByModuleIdAndCourse_CourseId(moduleId, courseId);
		
		if(module.isEmpty()) {
			//
		}
		
		return module;
	}

	@Override
	public Module updateModule(ModuleDTO moduleDto, Module module) {
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

}
