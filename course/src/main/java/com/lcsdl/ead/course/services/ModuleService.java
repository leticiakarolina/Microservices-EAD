package com.lcsdl.ead.course.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.lcsdl.ead.course.dtos.ModuleDTO;
import com.lcsdl.ead.course.models.Module;

public interface ModuleService {

	void deleteModule(Module module);
	
	List<Module> findAllModulesByCourse(UUID courseId);

	Module saveModule(UUID courseId, ModuleDTO moduleDto);

	Optional<Module> findOneModuleByCourse(UUID courseId, UUID moduleId);

	Module updateModule(UUID courseId, UUID moduleId, ModuleDTO moduleDto);
}
