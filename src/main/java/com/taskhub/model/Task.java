package com.taskhub.model;

import java.util.Date;
import java.util.List;

import com.taskhub.dto.TaskTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private Long id;
	
	@Column(name = "task_title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "task_status")
	private String status;
	
	@Column(name = "priorirty")
	private String priority;
	
	@Column(name = "creator")
	private String creator;
	
	@Column(name = "assignee")
	private String assignee;
	
	@Column(name = "comments")
	private List<String> comments;
	
	@Column(name = "created")
	private Date created;
	
	@Column(name = "updated")
	private Date updated;
	
	@Column(name = "due_date")
	private Date dueDate;

	public Task(Long id, String title, String description, String status, String priority, String creator, String assignee,
			List<String> comments, Date created, Date updated, Date dueDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.creator = creator;
		this.assignee = assignee;
		this.comments = comments;
		this.created = created;
		this.updated = updated;
		this.dueDate = dueDate;
	}

	public Task(TaskTO to) {
		super();
		this.title = to.getTitle();
		this.description = to.getDescription();
		this.status = to.getStatus();
		this.priority = to.getPriority();
		this.creator = to.getCreator();
		this.assignee = to.getAssignee();
		this.comments = to.getComments();
		this.created = to.getCreated();
		this.updated = to.getUpdated();
		this.dueDate = to.getDueDate();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public Date getCreatedAt() {
		return created;
	}

	public void setCreatedAt(Date createdAt) {
		this.created = createdAt;
	}

	public Date getUpdatedAt() {
		return updated;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updated = updatedAt;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", priority=" + priority + ", creator=" + creator + ", assignee=" + assignee + ", comments="
				+ comments + ", createdAt=" + created + ", updatedAt=" + updated + ", dueDate=" + dueDate + "]";
	}

}
