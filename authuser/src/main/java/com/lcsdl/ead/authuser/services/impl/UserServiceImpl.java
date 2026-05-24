package com.lcsdl.ead.authuser.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lcsdl.ead.authuser.dtos.UserDTO;
import com.lcsdl.ead.authuser.enums.UserStatus;
import com.lcsdl.ead.authuser.enums.UserType;
import com.lcsdl.ead.authuser.exceptions.NotFoundException;
import com.lcsdl.ead.authuser.models.User;
import com.lcsdl.ead.authuser.repositories.UserRepository;
import com.lcsdl.ead.authuser.services.UserService;
import com.lcsdl.ead.authuser.specifications.UserSpecification;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<User> findById(UUID id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new NotFoundException("User with id: " + id + " was not found.");
		}
		
		return user;
	}

	@Override
	public void delete(UUID id) {
		userRepository.delete(findById(id).get());
	}

	@Override
	public User registerUser(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		user.setUserStatus(UserStatus.ACTIVE);
		user.setUserType(UserType.USER);
		user.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
		user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		return userRepository.save(user);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User updateUser(UserDTO userDTO, User user) {
		user.setFullName(userDTO.fullName());
		user.setPhoneNumber(userDTO.phoneNumber());
		user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		return userRepository.save(user);
	}

	@Override
	public boolean updatePassword(UserDTO userDTO, User user) {
		if(!user.getPassword().equals(userDTO.oldPassword())) {
			return false;
		}
		else {
			user.setPassword(userDTO.password());
			user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
			userRepository.save(user);
			return true;
		}
	}

	@Override
	public User updateImage(UserDTO userDTO, User user) {
		user.setImageUrl(userDTO.imageUrl());
		user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		return userRepository.save(user);
	}

	@Override
	public Page<User> findAll(String userType, Pageable pageable) {
		Specification<User> isStudent = UserSpecification.isStudent(userType);
		
		return userRepository.findAll(isStudent, pageable);
	}

}
