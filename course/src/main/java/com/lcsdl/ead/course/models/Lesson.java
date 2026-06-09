package com.lcsdl.ead.course.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_LESSONS")
public class Lesson implements Serializable {

	private static final long serialVersionUID = 4261055681555735368L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID lessonId;

	@Column(nullable = false, length = 50)
	private String title;

	@Column(nullable = false, length = 255)
	private String description;

	@Column(nullable = false, length = 255)
	private String videoUrl;

	@Column(nullable = false)
	private LocalDateTime creationDate;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Module module;

	public UUID getLessonId() {
		return lessonId;
	}

	public void setLessonId(UUID lessonId) {
		this.lessonId = lessonId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
