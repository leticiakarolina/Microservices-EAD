package com.lcsdl.ead.course.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.lcsdl.ead.course.dtos.LessonDTO;
import com.lcsdl.ead.course.models.Lesson;

public interface LessonService {

	void deleteLesson(UUID moduleId, UUID lessonId);
	
	void deleteLessons(List<Lesson> lessons);
	
	List<Lesson> findAllLessonByModuleId(UUID moduleId);

	Lesson saveLesson(UUID moduleId, LessonDTO lessonDto);

	Optional<Lesson> findOneLessonByModule(UUID moduleId, UUID lessonId);

	Lesson updateLesson(UUID moduleId, UUID lessonId, LessonDTO lessonDto);

}
