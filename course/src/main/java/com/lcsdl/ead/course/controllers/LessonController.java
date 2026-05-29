package com.lcsdl.ead.course.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lcsdl.ead.course.dtos.LessonDTO;
import com.lcsdl.ead.course.services.LessonService;
import com.lcsdl.ead.course.services.ModuleService;

@RestController
public class LessonController {

	private final ModuleService moduleService;
	private final LessonService lessonService;

	public LessonController(ModuleService moduleService, LessonService lessonService) {
		this.lessonService = lessonService;
		this.moduleService = moduleService;
	}
	
	@PostMapping("/modules/{moduleId}/lessons")
	public ResponseEntity<Object> saveLesson(@PathVariable UUID moduleId, @RequestBody @Validated LessonDTO lessonDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.saveLesson(moduleService.getModuleById(moduleId).get(), lessonDto));
	}
}
