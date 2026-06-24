package com.lcsdl.ead.course.specifications.filters;

import com.lcsdl.ead.course.enums.CourseLevel;

public record CourseSearchFilter(
	String name,
	CourseLevel courseLevel) {

}
