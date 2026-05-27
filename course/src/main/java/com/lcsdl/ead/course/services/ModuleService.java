package com.lcsdl.ead.course.services;

import java.util.List;
import java.util.UUID;

import com.lcsdl.ead.course.dtos.ModuleDTO;
import com.lcsdl.ead.course.models.Course;
import com.lcsdl.ead.course.models.Module;

public interface ModuleService {

	void deleteModule(Module module);
	
	List<Module> findAllModulesByCourseId(UUID courseId);

	Module saveModule(Course course, ModuleDTO moduleDto);
}
