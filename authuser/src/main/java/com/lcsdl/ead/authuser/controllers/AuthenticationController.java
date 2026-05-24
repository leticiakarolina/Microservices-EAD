package com.lcsdl.ead.authuser.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.lcsdl.ead.authuser.dtos.UserDTO;
import com.lcsdl.ead.authuser.services.UserService;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	private final UserService userService;

	public AuthenticationController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserDTO.UserView.RegistrationPost.class)
								@JsonView(UserDTO.UserView.RegistrationPost.class) UserDTO userDTO){
		if(userService.existsByUsername(userDTO.username())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken");
		}
		
		if(userService.existsByEmail(userDTO.email())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail is already taken");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userDTO));
	}
	
}
