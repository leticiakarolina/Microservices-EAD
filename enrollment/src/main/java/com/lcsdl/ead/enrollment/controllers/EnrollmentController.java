package com.lcsdl.ead.enrollment.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcsdl.ead.enrollment.dtos.EnrollmentRequestDTO;
import com.lcsdl.ead.enrollment.services.EnrollmentService;

@RestController
@RequestMapping("enrollment")
public class EnrollmentController {

	private final EnrollmentService enrollmentService;

	public EnrollmentController(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}
	
	@PostMapping
	public ResponseEntity<Object> createEnrollment(@RequestBody EnrollmentRequestDTO enrollmentDto){
		return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.createEnrollment(enrollmentDto));
	}
	
	@GetMapping("/students/{studentId}")
	public ResponseEntity<Object> getCoursesByStudentId(@PathVariable UUID studentId){
		return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getCoursesByStudentId(studentId));
	}
	
	@GetMapping("/courses/{courseId}")
	public ResponseEntity<Object> getStudentsByCourseId(@PathVariable UUID courseId){
		return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getStudentsByCourseId(courseId));
	}

}
