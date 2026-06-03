package com.lcsdl.ead.course.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.lcsdl.ead.course.dtos.LessonDTO;
import com.lcsdl.ead.course.models.Lesson;
import com.lcsdl.ead.course.models.Module;
import com.lcsdl.ead.course.repositories.LessonRepository;
import com.lcsdl.ead.course.repositories.ModuleRepository;
import com.lcsdl.ead.course.services.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	private final LessonRepository lessonRepository;
	private final ModuleRepository moduleRepository;
	
	public LessonServiceImpl(LessonRepository lessonRepository, ModuleRepository moduleRepository) {
		this.lessonRepository = lessonRepository;
		this.moduleRepository = moduleRepository;
	}

	@Override
	public void deleteLesson(UUID moduleId, UUID lessonId) {
		Optional<Lesson> lesson = findOneLessonByModule(moduleId, lessonId);
		lessonRepository.delete(lesson.get());
	}

	@Override
	public void deleteLessons(List<Lesson> lessons) {
		lessonRepository.deleteAll(lessons);
	}

	@Override
	public List<Lesson> findAllLessonByModuleId(UUID moduleId) {
		getModuleById(moduleId);
		return lessonRepository.findAllByModule_ModuleId(moduleId);
	}

	@Override
	public Lesson saveLesson(UUID moduleId, LessonDTO lessonDto) {
		Module module = getModuleById(moduleId).get();
		
		Lesson lesson = new Lesson();
		BeanUtils.copyProperties(lessonDto, lesson);
		lesson.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		lesson.setModule(module);
		
		return lessonRepository.save(lesson);
	}

	@Override
	public Optional<Lesson> findOneLessonByModule(UUID moduleId, UUID lessonId) {
		Module module = getModuleById(moduleId).get();
		
		Optional<Lesson> lesson = lessonRepository.findByLessonIdAndModule_ModuleId(lessonId, module.getModuleId());
		
		if(lesson.isEmpty()) {
			//retornar erro
		}
		
		return lesson;
	}

	
	private Optional<Module> getModuleById(UUID moduleId) {
		Optional<Module> module = moduleRepository.findById(moduleId);
		
		if(module.isEmpty()){
			//
		}
		
		return module;
	}

	@Override
	public Lesson updateLesson(UUID moduleId, UUID lessonId, LessonDTO lessonDto) {
		Lesson lesson = findOneLessonByModule(moduleId, lessonId).get();
		BeanUtils.copyProperties(lessonDto, lesson);
		return lessonRepository.save(lesson);
	}
	
	
}
