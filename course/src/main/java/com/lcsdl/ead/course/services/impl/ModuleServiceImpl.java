package com.lcsdl.ead.course.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcsdl.ead.course.dtos.ModuleDTO;
import com.lcsdl.ead.course.models.Course;
import com.lcsdl.ead.course.models.Module;
import com.lcsdl.ead.course.repositories.ModuleRepository;
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
		return moduleRepository.findAllByCourse_CourseId(courseId);
	}

	@Override
	@Transactional
	public Module saveModule(Course course, ModuleDTO moduleDto) {
		Module module = createModule(moduleDto, course);
		return module;
	}
	
	private Module createModule(ModuleDTO moduleDto, Course course) {
		Module module = new Module();
		BeanUtils.copyProperties(moduleDto, module);
		module.setCourse(course);
		module.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		return moduleRepository.save(module);
	}
	
}
