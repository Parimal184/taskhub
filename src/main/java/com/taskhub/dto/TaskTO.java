package com.taskhub.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.taskhub.common.TaskPriority;
import com.taskhub.common.TaskStatus;
import com.taskhub.model.Task;

public class TaskTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8659141815859846253L;

	private Long id;
	
	private String title;
	
	private String description;
	
	private TaskStatus status;
	
	private TaskPriority priority;
	
	private Date created;
	
	private Date updated;
	
	private Date dueDate;
	
	private String creatorEmail;
	
	public TaskTO() {}

	
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
		if(status == null) {
			status = TaskStatus.TODO;
		}
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public Date getCreated() {
		if(created == null) {
			created = new Date();
		}
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		if(updated == null) {
			updated = new Date();
		}
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

	public String getCreatorEmail() {
		return creatorEmail;
	}

	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}
	
}
