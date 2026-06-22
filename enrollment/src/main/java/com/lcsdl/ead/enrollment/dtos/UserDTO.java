package com.lcsdl.ead.enrollment.dtos;

import java.util.UUID;

import com.lcsdl.ead.enrollment.enums.UserStatus;

public record UserDTO(
	UUID userId,
	String email,
	String fullName,
	UserStatus status) {
}
