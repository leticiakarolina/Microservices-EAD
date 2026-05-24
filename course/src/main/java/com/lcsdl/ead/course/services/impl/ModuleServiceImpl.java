package com.lcsdl.ead.course.services.impl;

import org.springframework.stereotype.Service;

import com.lcsdl.ead.course.enums.repositories.ModuleRepository;
import com.lcsdl.ead.course.services.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	private final ModuleRepository moduleRepository;

	public ModuleServiceImpl(ModuleRepository moduleRepository) {
		this.moduleRepository = moduleRepository;
	}
	
	
}
