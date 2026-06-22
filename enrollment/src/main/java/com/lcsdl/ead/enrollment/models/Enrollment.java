package com.lcsdl.ead.enrollment.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.lcsdl.ead.enrollment.enums.EnrollmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ENROLLMENTS")
public class Enrollment implements Serializable {

	private static final long serialVersionUID = -9062522897517546319L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID enrollmentId;
	
	@Column(nullable = false)
	private UUID userId;
	
	@Column(nullable = false)
	private UUID courseId;
	
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

	public UUID getCourseId() {
		return courseId;
	}

	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
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
