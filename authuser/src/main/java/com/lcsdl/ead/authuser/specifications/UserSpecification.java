package com.lcsdl.ead.authuser.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.lcsdl.ead.authuser.models.User;

import jakarta.persistence.criteria.CriteriaBuilder;

public class UserSpecification {
	
	private CriteriaBuilder criteriaBuilder;

	public static Specification<User> isStudent(String userType) {
        return (root, query, criteriaBuilder) -> {
        	if(userType == null || userType.isBlank()) {
        		return null;
        	} else {
        		return criteriaBuilder.equal(root.get("userType"), userType);
        	}
        };
    }
}
