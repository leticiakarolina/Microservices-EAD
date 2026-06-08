package com.lcsdl.ead.course.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.lcsdl.ead.course.models.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, UUID>, JpaSpecificationExecutor<Module>{
	 
	 List<Module> findAllByCourse_CourseId(UUID courseId);

	 Optional<Module> findByModuleIdAndCourse_CourseId(UUID moduleId, UUID courseId);

}
