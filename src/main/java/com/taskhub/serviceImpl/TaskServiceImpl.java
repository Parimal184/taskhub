package com.taskhub.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskhub.model.Task;
import com.taskhub.repository.TaskRepository;
import com.taskhub.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository repository;
	
	@Override
	public void saveTask(Task task) {
		repository.save(task);
	}

	@Override
	public Task getTaskById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTask(Long id) {
		// TODO Auto-generated method stub
		
	}

}
