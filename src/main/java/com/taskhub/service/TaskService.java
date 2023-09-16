package com.taskhub.service;

import java.util.List;

import com.taskhub.common.TaskStatus;
import com.taskhub.dto.TaskTO;
import com.taskhub.model.Task;
import com.taskhub.model.User;

public interface TaskService {
		
	Task getTaskById(Long id);
	
	Task saveOrUpdateTask(TaskTO task);
	
	List<Task> getAllTasks();
	
	List<Task> getAllByUser(User user);
	
	void delete(Task task);
	
	List<Task> getTasksByStatus(TaskStatus status);
	
	List<Task> getTasksByUserAndStatus(TaskStatus status, User user);
}
