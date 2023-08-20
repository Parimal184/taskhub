package com.taskhub.service;

import java.util.List;

import com.taskhub.model.Task;

public interface TaskService {
	
	void saveTask(Task task);
	
	Task getTaskById(Long id);
	
	void updateTask(Task task);
	
	List<Task> getAllTasks(); 
}
