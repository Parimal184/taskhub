package com.taskhub.model;

import java.io.Serializable;
import java.util.Date;

import com.taskhub.dto.TaskTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2533177731820540366L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private Long id;
	
	@Column(name = "task_title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TaskStatus status;
	
	@Column(name = "priorirty")
	private String priority;
	
	@Column(name = "created")
	private Date created;
	
	@Column(name = "updated")
	private Date updated;
	
	@Column(name = "due_date")
	private Date dueDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	public Task() {}

	public Task(Long id, String title, String description, TaskStatus status, String priority, Date created,
			Date updated, Date dueDate, User user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.created = created;
		this.updated = updated;
		this.dueDate = dueDate;
		this.user = user;
	}	

	public Task(TaskTO taskTo) {
		this.title = taskTo.getTitle();
		this.description = taskTo.getDescription();
		this.status = taskTo.getStatus();
		this.priority = taskTo.getPriority();
		this.created = taskTo.getCreated();
		this.updated = taskTo.getUpdated();
		this.dueDate = taskTo.getDueDate();
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

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

		
}
