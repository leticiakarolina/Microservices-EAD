package com.lcsdl.ead.course.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.lcsdl.ead.course.enums.CourseLevel;
import com.lcsdl.ead.course.models.Course;

import jakarta.persistence.criteria.CriteriaBuilder;

public class CourseSpecifications {

	private CriteriaBuilder criteriaBuilder;

	public static Specification<Course> courseLevel(CourseLevel courseLevel){
        return (root, query, builder) -> courseLevel == null ? null : builder.equal(root.get("courseLevel"), courseLevel);
	}
	
    public static Specification<Course> likeName(String name) {
        return (root, query, builder) -> !StringUtils.hasText(name) ? null : 
            builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
}
