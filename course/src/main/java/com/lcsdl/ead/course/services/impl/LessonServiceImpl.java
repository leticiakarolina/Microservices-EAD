package com.lcsdl.ead.course.services.impl;

import org.springframework.stereotype.Service;

import com.lcsdl.ead.course.enums.repositories.LessonRepository;
import com.lcsdl.ead.course.services.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	private final LessonRepository lessonRepository;

	public LessonServiceImpl(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}
	
	
}
