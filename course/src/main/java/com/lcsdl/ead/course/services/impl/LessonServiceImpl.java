package com.lcsdl.ead.course.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lcsdl.ead.course.enums.repositories.LessonRepository;
import com.lcsdl.ead.course.models.Lesson;
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
		return lessonRepository.findAllLessonByModuleId(moduleId);
	}
	
	
}
