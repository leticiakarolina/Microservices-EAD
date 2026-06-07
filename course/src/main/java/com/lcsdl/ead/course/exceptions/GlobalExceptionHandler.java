package com.lcsdl.ead.course.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import tools.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorRecordResponse> handleNotFoundException(NotFoundException ex){
		ErrorRecordResponse errorResponse = new ErrorRecordResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorRecordResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
		ErrorRecordResponse errorResponse = new ErrorRecordResponse(HttpStatus.BAD_REQUEST.value(), "Error - validation failed ", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorRecordResponse> handleInvalidFormatException(HttpMessageNotReadableException ex){
		Map<String, String> errors = new HashMap<>();
		if(ex.getCause() instanceof InvalidFormatException) {
			InvalidFormatException ifx = (InvalidFormatException) ex.getCause();
			if(ifx.getTargetType() != null && ifx.getTargetType().isEnum()) {
				String fieldName = ifx.getPath().get(ifx.getPath().size() -1).getPropertyName();
				String errorMessage = ex.getMessage();
				errors.put(fieldName, errorMessage);
			}
		}
		
		ErrorRecordResponse errorResponse = new ErrorRecordResponse(HttpStatus.BAD_REQUEST.value(), "Error - invalid enum value  ", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
}
