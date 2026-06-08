package com.lcsdl.ead.course.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import com.lcsdl.ead.course.models.Module;
import jakarta.persistence.criteria.CriteriaBuilder;

public class ModuleSpecifications {
	
	private CriteriaBuilder builder;

    public static Specification<Module> courseId(UUID courseId) {
        return (root, query, builder) ->
                courseId == null
                        ? null
                        : builder.equal(root.get("course").get("courseId"), courseId);
    }

    public static Specification<Module> likeTitle(String title) {
        return (root, query, builder) ->
                title == null || title.isBlank()
                        ? null
                        : builder.like(
                                builder.lower(root.get("title")),
                                "%" + title.toLowerCase() + "%"
                        );
    }
}
