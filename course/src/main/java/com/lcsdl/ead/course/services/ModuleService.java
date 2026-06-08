package com.lcsdl.ead.course.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lcsdl.ead.course.dtos.ModuleDTO;
import com.lcsdl.ead.course.models.Module;
import com.lcsdl.ead.course.specifications.filters.ModuleSearchFilter;

public interface ModuleService {

	void deleteModule(Module module);
	
	Page<Module> findAllModulesByCourse(Pageable pageable, ModuleSearchFilter moduleFilter, UUID courseId);

	Module saveModule(UUID courseId, ModuleDTO moduleDto);

	Optional<Module> findOneModuleByCourse(UUID courseId, UUID moduleId);

	Module updateModule(UUID courseId, UUID moduleId, ModuleDTO moduleDto);
	
	List<Module> findAllModulesByCourse(UUID courseId);
}
