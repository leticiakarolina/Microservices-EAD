package com.lcsdl.ead.authuser.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.lcsdl.ead.authuser.dtos.UserDTO;
import com.lcsdl.ead.authuser.models.User;
import com.lcsdl.ead.authuser.services.UserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<Page<User>> getAllUsers(@RequestParam(required = false) String userType, Pageable pageable){
		Page<User> userPage = userService.findAll(userType , pageable);
		if(!userPage.isEmpty()) {
			for(User user : userPage.toList()) {
				user.add(linkTo(methodOn(UserController.class).getOneUser(user.getUserId())).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(userPage);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("User deleted with success!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, 
										@RequestBody @Validated(UserDTO.UserView.UserPut.class) 
										@JsonView(UserDTO.UserView.UserPut.class) UserDTO userDTO){
		return ResponseEntity.status(HttpStatus.OK).body(
				userService.updateUser(userDTO, userService.findById(id).get()));
	}
	
	@PutMapping("/{id}/password")
	public ResponseEntity<Object> updatePassword(@PathVariable(value = "id") UUID id,
										@RequestBody @Validated(UserDTO.UserView.PasswordPut.class)
										@JsonView(UserDTO.UserView.PasswordPut.class) UserDTO userDTO){
		if(!userService.updatePassword(userDTO, userService.findById(id).get())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: mismatched old password");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully");
	}
	
	@PutMapping("/{id}/image")
	public ResponseEntity<Object> updateImage(@PathVariable(value = "id") UUID id,
										@RequestBody @Validated(UserDTO.UserView.ImagePut.class)
										@JsonView(UserDTO.UserView.ImagePut.class) UserDTO userDTO){
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateImage(userDTO, userService.findById(id).get()));
	}
}
