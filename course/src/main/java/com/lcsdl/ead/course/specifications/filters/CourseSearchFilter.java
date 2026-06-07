package com.lcsdl.ead.course.specifications.filters;

import com.lcsdl.ead.course.enums.CourseLevel;
import com.lcsdl.ead.course.enums.CourseStatus;

public record CourseSearchFilter(
	String name,
	CourseStatus courseStatus,
	CourseLevel courseLevel) {

}
