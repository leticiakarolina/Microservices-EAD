package com.lcsdl.ead.course.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.lcsdl.ead.course.dtos.LessonDTO;
import com.lcsdl.ead.course.models.Lesson;
import com.lcsdl.ead.course.models.Module;
import com.lcsdl.ead.course.repositories.LessonRepository;
import com.lcsdl.ead.course.services.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	private final LessonRepository lessonRepository;
	
	public LessonServiceImpl(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	@Override
	public void deleteLesson(Lesson lesson) {
		lessonRepository.delete(lesson);
	}

	@Override
	public void deleteLessons(List<Lesson> lessons) {
		lessonRepository.deleteAll(lessons);
	}

	@Override
	public List<Lesson> findAllLessonByModuleId(UUID moduleId) {
		return lessonRepository.findAllByModule_ModuleId(moduleId);
	}

	@Override
	public Lesson saveLesson(Module module, LessonDTO lessonDto) {
		Lesson lesson = new Lesson();
		BeanUtils.copyProperties(lessonDto, lesson);
		lesson.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		lesson.setModule(module);
		
		return lessonRepository.save(lesson);
	}
	
	
}
