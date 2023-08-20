package com.taskhub.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		Task task = repository.getTaskById(id);
		return task;
	}

	@Override
	public void updateTask(Task task) {
		repository.save(task);
	}

	@Override
	public List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<>();
		tasks = repository.findAll();
		return new ArrayList<>(tasks);
	}

}
