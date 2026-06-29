package com.lcsdl.ead.enrollment.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.lcsdl.ead.enrollment.dtos.CourseDTO;
import com.lcsdl.ead.enrollment.exception.NotFoundException;
import com.lcsdl.ead.enrollment.gateways.CourseGateway;

@Component
public class CourseClient implements CourseGateway {
	
	private final RestClient restClient;
	
	public CourseClient(RestClient.Builder restClientBuilder, @Value("${microservices.course}") String courseUrl) {
		this.restClient = restClientBuilder
                .baseUrl(courseUrl)
                .build();
	}

	@Override
	public CourseDTO findCourseById(UUID courseId) {
		return restClient.get()
				.uri("courses/{courseId}", courseId)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    if (response.getStatusCode().value() == 404) {
                    	throw new NotFoundException("User with id " + courseId + " was not found");
                    }
                })
                .body(CourseDTO.class);
	}

}
