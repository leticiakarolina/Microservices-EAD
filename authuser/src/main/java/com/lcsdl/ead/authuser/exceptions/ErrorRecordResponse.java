package com.lcsdl.ead.authuser.exceptions;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorRecordResponse(
	int errorCode,
	String errorMessage,
	Map<String, String> errorDetails) {

}
