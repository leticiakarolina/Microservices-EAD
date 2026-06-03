package com.lcsdl.ead.course.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lcsdl.ead.course.dtos.LessonDTO;
import com.lcsdl.ead.course.services.LessonService;

@RestController
public class LessonController {

	private final LessonService lessonService;

	public LessonController(LessonService lessonService) {
		this.lessonService = lessonService;
	}
	
	@PostMapping("/modules/{moduleId}/lessons")
	public ResponseEntity<Object> saveLesson(@PathVariable UUID moduleId, @RequestBody @Validated LessonDTO lessonDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.saveLesson(moduleId, lessonDto));
	}
	
	@GetMapping("/modules/{moduleId}/lessons")
	public ResponseEntity<Object> getLessonsByModule(@PathVariable UUID moduleId){
		return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAllLessonByModuleId(moduleId));
	}
	
	@GetMapping("/modules/{moduleId}/lessons/{lessonId}")
	public ResponseEntity<Object> getOneLessonByModule(@PathVariable UUID moduleId, @PathVariable UUID lessonId){
		return ResponseEntity.status(HttpStatus.OK).body(
			lessonService.findOneLessonByModule(moduleId, lessonId));
	}
	
	@DeleteMapping("/modules/{moduleId}/lessons/{lessonId}")
	public ResponseEntity<Object> deleteModule(@PathVariable UUID moduleId, @PathVariable UUID lessonId){
		lessonService.deleteLesson(moduleId, lessonId);
		return ResponseEntity.status(HttpStatus.OK).body("Module deleted with success!");
	}
	
	@PutMapping("/modules/{moduleId}/lessons/{lessonId}")
	public ResponseEntity<Object> updateModule(@PathVariable UUID moduleId, @PathVariable UUID lessonId, @RequestBody @Validated LessonDTO lessonDto){
		return ResponseEntity.status(HttpStatus.OK).body(
				lessonService.updateLesson(moduleId, lessonId, lessonDto));
	}
}
