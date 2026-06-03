package com.lcsdl.ead.course.controllers;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcsdl.ead.course.dtos.CourseDTO;
import com.lcsdl.ead.course.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@PostMapping()
	public ResponseEntity<Object> saveCourse(@RequestBody CourseDTO courseDto){
		if(courseService.existsByName(courseDto.name())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Course name is already taken");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.saveCourse(courseDto));
	}
	
	@GetMapping
	public ResponseEntity<Object> getCourses(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourses(pageable));
	}
	
	@GetMapping("/{courseId}")
	public ResponseEntity<Object> getCourseById(@PathVariable UUID courseId){
		return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(courseId).get());
	}
	
	@DeleteMapping("/{courseId}")
	public ResponseEntity<Object> deleteCourse(@PathVariable UUID courseId){
		courseService.deleteCourse(courseId);
		return ResponseEntity.status(HttpStatus.OK).body("Course deleted with success!");
	}
	
	@PutMapping("/{courseId}")
	public ResponseEntity<Object> updateCourse(@PathVariable UUID courseId, @RequestBody @Validated CourseDTO courseDto){
		return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(courseDto, courseId));
	}
}
