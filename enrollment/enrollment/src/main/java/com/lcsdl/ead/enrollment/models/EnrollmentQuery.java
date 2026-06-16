package com.lcsdl.ead.enrollment.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.lcsdl.ead.enrollment.enums.EnrollmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ENROLLMENTS_QUERY")
public class EnrollmentQuery implements Serializable {
	
	private static final long serialVersionUID = 2314189135877974111L;

	@Id
	private UUID enrollmentId;

	@Column(nullable = false)
	private UUID userId;
	
	@Column(nullable = false, length = 150)
	private String userFullName;
	
	@Column(nullable = false)
	private UUID courseId;
	
	@Column(nullable = false, length = 50)
	private String courseName;
	
	@Column(nullable = false)
	private LocalDateTime enrolledAt;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnrollmentStatus status;

	public UUID getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(UUID enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public UUID getCourseId() {
		return courseId;
	}

	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public LocalDateTime getEnrolledAt() {
		return enrolledAt;
	}

	public void setEnrolledAt(LocalDateTime enrolledAt) {
		this.enrolledAt = enrolledAt;
	}

	public EnrollmentStatus getStatus() {
		return status;
	}

	public void setStatus(EnrollmentStatus status) {
		this.status = status;
	}

}
