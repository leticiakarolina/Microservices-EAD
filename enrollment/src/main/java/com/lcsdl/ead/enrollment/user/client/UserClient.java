package com.lcsdl.ead.enrollment.user.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.lcsdl.ead.enrollment.dtos.UserDTO;
import com.lcsdl.ead.enrollment.services.UserGateway;

@Component
public class UserClient implements UserGateway {
	
	private final RestClient restClient;
	
	public UserClient(RestClient.Builder restClientBuilder, @Value("${microservices.auth-user}") String authUserUrl) {
		this.restClient = restClientBuilder
                .baseUrl(authUserUrl)
                .build();
	}

	@Override
	public UserDTO findUserById(UUID userId) {
		return restClient.get()
				.uri("/users/{id}", userId)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    if (response.getStatusCode().value() == 404) {
                        throw new RuntimeException("User not found"); 
                    }
                })
                .body(UserDTO.class);
	}

}
