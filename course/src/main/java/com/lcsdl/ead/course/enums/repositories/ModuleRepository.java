package com.lcsdl.ead.course.enums.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lcsdl.ead.course.models.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, UUID> {
	
	List<Module> findAllModuleByCourseId(UUID courseId);

}
