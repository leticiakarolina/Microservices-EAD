package com.lcsdl.ead.course.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.lcsdl.ead.course.models.Lesson;

import jakarta.persistence.criteria.CriteriaBuilder;

public class LessonSpecifications {
	
	private CriteriaBuilder builder;
	
    public static Specification<Lesson> moduleId(UUID moduleId) {
        return (root, query, builder) ->
                moduleId == null
                        ? null
                        : builder.equal(root.get("module").get("moduleId"), moduleId);
    }
	
    public static Specification<Lesson> likeName(String title) {
        return (root, query, builder) -> !StringUtils.hasText(title) ? null : 
            builder.like(builder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

}
