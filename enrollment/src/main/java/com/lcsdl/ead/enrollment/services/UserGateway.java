package com.lcsdl.ead.enrollment.services;

import java.util.UUID;

import com.lcsdl.ead.enrollment.dtos.UserDTO;

public interface UserGateway {
	
	UserDTO findUserById(UUID userId);

}
