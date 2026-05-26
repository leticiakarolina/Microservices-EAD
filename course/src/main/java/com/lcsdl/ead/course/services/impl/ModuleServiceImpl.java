package com.lcsdl.ead.course.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lcsdl.ead.course.enums.repositories.ModuleRepository;
import com.lcsdl.ead.course.models.Module;
import com.lcsdl.ead.course.services.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	private final ModuleRepository moduleRepository;

	public ModuleServiceImpl(ModuleRepository moduleRepository) {
		this.moduleRepository = moduleRepository;
	}

	@Override
	public void deleteModule(Module module) {
		moduleRepository.delete(module);
	}

	@Override
	public List<Module> findAllModulesByCourseId(UUID courseId) {
		return moduleRepository.findAllModuleByCourseId(courseId);
	}
	
	
	
}
