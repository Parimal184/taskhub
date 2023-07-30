package com.taskhub.service;

import com.taskhub.model.Task;

public interface TaskService {
	
	void saveTask(Task task);
	
	Task getTaskById(Long id);
	
	void updateTask(Long id);
}
