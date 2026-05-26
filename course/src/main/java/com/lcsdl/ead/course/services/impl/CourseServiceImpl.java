package com.lcsdl.ead.course.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lcsdl.ead.course.enums.repositories.CourseRepository;
import com.lcsdl.ead.course.models.Lesson;
import com.lcsdl.ead.course.models.Module;
import com.lcsdl.ead.course.services.CourseService;
import com.lcsdl.ead.course.services.LessonService;
import com.lcsdl.ead.course.services.ModuleService;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final ModuleService moduleService;
	private final LessonService lessonService;

	public CourseServiceImpl(CourseRepository courseRepository, ModuleService moduleService, LessonService lessonService) {
		this.courseRepository = courseRepository;
		this.moduleService = moduleService;
		this.lessonService = lessonService;
	}

	@Override
	public void deleteCourse(UUID courseId) {
		List<Module> modules = moduleService.findAllModulesByCourseId(courseId);
		
		if(!modules.isEmpty()) {
			for(Module module : modules) {
				List<Lesson> lessons = lessonService.findAllLessonByModuleId(module.getModuleId());
				
				if(!lessons.isEmpty()) {
					lessonService.deleteLessons(lessons);
				}
				moduleService.deleteModule(module);
			}
		}
		courseRepository.deleteById(courseId);
	}
	
	
}
