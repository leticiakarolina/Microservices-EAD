package com.lcsdl.ead.authuser.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lcsdl.ead.authuser.dtos.UserDTO;
import com.lcsdl.ead.authuser.models.User;

public interface UserService {
	
	Optional<User> findById(UUID id);
	
	void delete (UUID id);

	User registerUser(UserDTO userDTO);
	
	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	User updateUser(UserDTO userDTO, User user);

	boolean updatePassword(UserDTO userDTO, User user);

	User updateImage(UserDTO userDTO, User user);

	Page<User> findAll(String userType, Pageable pageable);
	
}
