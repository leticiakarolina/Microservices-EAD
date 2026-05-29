package com.lcsdl.ead.course.services;

import java.util.List;
import java.util.UUID;

import com.lcsdl.ead.course.dtos.LessonDTO;
import com.lcsdl.ead.course.models.Lesson;
import com.lcsdl.ead.course.models.Module;

public interface LessonService {

	void deleteLesson(Lesson lesson);
	
	void deleteLessons(List<Lesson> lessons);
	
	List<Lesson> findAllLessonByModuleId(UUID moduleId);

	Lesson saveLesson(Module module, LessonDTO lessonDto);
}
