package com.lcsdl.ead.enrollment.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lcsdl.ead.enrollment.models.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID>{

	boolean existsByUserIdAndCourseId(UUID userId, UUID courseId);
}
