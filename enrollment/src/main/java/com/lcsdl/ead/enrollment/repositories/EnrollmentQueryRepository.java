package com.lcsdl.ead.enrollment.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lcsdl.ead.enrollment.models.EnrollmentQuery;

@Repository
public interface EnrollmentQueryRepository extends JpaRepository<EnrollmentQuery, UUID> {

}
