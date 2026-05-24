package com.lcsdl.ead.course.enums.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lcsdl.ead.course.models.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {

}
