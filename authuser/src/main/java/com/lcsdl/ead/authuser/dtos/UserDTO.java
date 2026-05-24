package com.lcsdl.ead.authuser.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import com.lcsdl.ead.authuser.validations.PasswordConstraint;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
	@NotBlank(message = "Username is required", groups = UserView.RegistrationPost.class)
	@Size(min = 4, max = 50, message = "Size must be between 4 and 50", groups = UserView.RegistrationPost.class)
	@JsonView(UserView.RegistrationPost.class)
	String username,
	
	@NotBlank(message = "E-mail is mandatory", groups = UserView.RegistrationPost.class)
	@Email(message = "E-mail must be in expected form", groups = UserView.RegistrationPost.class)
	@JsonView(UserView.RegistrationPost.class)
	String email,
	
	@PasswordConstraint(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	String password,
	
	@PasswordConstraint(groups = UserView.PasswordPut.class)
	@JsonView(UserView.PasswordPut.class)
	String oldPassword,
	
	@NotBlank(message = "A full name is mandatory", groups = {UserView.RegistrationPost.class, UserView.UserPut.class})
	@JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
	String fullName,
	
	@JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
	String phoneNumber,
	
	@NotBlank(message = "A image URL mandatory when updating the image", groups = UserView.ImagePut.class)
	@JsonView(UserView.ImagePut.class)
	String imageUrl) {
	
	public interface UserView{
		interface RegistrationPost{}
		interface UserPut{}
		interface PasswordPut{}
		interface ImagePut{}
	}

}
