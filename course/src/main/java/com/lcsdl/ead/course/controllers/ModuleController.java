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

import com.lcsdl.ead.course.dtos.ModuleDTO;
import com.lcsdl.ead.course.services.CourseService;
import com.lcsdl.ead.course.services.ModuleService;

@RestController
public class ModuleController {

	private final ModuleService moduleService;
	private final CourseService courseService;

	public ModuleController(ModuleService moduleService, CourseService courseService) {
		this.moduleService = moduleService;
		this.courseService = courseService;
	}
	
	@PostMapping("/courses/{courseId}/modules")
	public ResponseEntity<Object> saveModule(@PathVariable UUID courseId, @RequestBody @Validated ModuleDTO moduleDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.saveModule(courseService.getCourseById(courseId).get(), moduleDto));
	}
	
	@GetMapping("/courses/{courseId}/modules")
	public ResponseEntity<Object> getModulesByCourse(@PathVariable UUID courseId){
		return ResponseEntity.status(HttpStatus.OK).body(moduleService.findAllModulesByCourse(courseId));
	}
	
	@GetMapping("/courses/{courseId}/modules/{moduleId}")
	public ResponseEntity<Object> getOneModule(@PathVariable UUID courseId, @PathVariable UUID moduleId){
		return ResponseEntity.status(HttpStatus.OK).body(moduleService.findOneModuleByCourse(courseId, moduleId));
	}
	
	@DeleteMapping("/courses/{courseId}/modules/{moduleId}")
	public ResponseEntity<Object> deleteModule(@PathVariable UUID courseId, @PathVariable UUID moduleId){
		moduleService.deleteModule(moduleService.findOneModuleByCourse(courseId, moduleId).get());
		return ResponseEntity.status(HttpStatus.OK).body("Module deleted with success!");
	}
	
	@PutMapping("/courses/{courseId}/modules/{moduleId}")
	public ResponseEntity<Object> updateModule(@PathVariable UUID courseId, @PathVariable UUID moduleId, @RequestBody @Validated ModuleDTO moduleDto){
		return ResponseEntity.status(HttpStatus.OK).body(moduleService.updateModule(moduleDto, moduleService.findOneModuleByCourse(courseId, moduleId).get()));
	}
}
