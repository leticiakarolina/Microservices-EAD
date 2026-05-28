package com.lcsdl.ead.course.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.lcsdl.ead.course.dtos.ModuleDTO;
import com.lcsdl.ead.course.models.Course;
import com.lcsdl.ead.course.models.Module;

public interface ModuleService {

	void deleteModule(Module module);
	
	List<Module> findAllModulesByCourse(UUID courseId);

	Module saveModule(Course course, ModuleDTO moduleDto);

	Optional<Module> findOneModuleByCourse(UUID courseId, UUID moduleId);

	Module updateModule(ModuleDTO moduleDto, Module module);
}
