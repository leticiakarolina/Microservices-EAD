package com.lcsdl.ead.course.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lcsdl.ead.course.models.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {

	@Modifying
    @Query("DELETE FROM Lesson l WHERE l.module.moduleId = :moduleId")
    void deleteLessonsByModuleId(UUID moduleId);
	
	List<Lesson> findAllByModule_ModuleId(UUID moduleId);
	
	Optional<Lesson> findByLessonIdAndModule_ModuleId(UUID lessonId, UUID moduleId);
}
