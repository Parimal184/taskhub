package com.taskhub.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskhub.common.TaskStatus;
import com.taskhub.dto.TaskTO;
import com.taskhub.model.Task;
import com.taskhub.model.User;
import com.taskhub.repository.TaskRepository;
import com.taskhub.service.TaskService;
import com.taskhub.service.UserService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository repository;
	
	@Autowired
	UserService userService;

	@Override
	public Task getTaskById(Long id) {
		Optional<Task> task = repository.findById(id); 
		return task.orElse(null);
	}

	@Override
	public Task saveOrUpdateTask(TaskTO taskTo) {
		Task task = null;
		
		if(taskTo.getId() != null) {
			task = getTaskById(taskTo.getId());	
			task.setTitle(taskTo.getTitle());
			task.setDescription(taskTo.getDescription());
			task.setDueDate(taskTo.getDueDate());
			task.setPriority(taskTo.getPriority());
			task.setStatus(taskTo.getStatus());
			task.setUpdated(new Date());
		} else {			
			User currentUser = userService.findByEmail(taskTo.getCreatorEmail());
			task = new Task(taskTo);
			task.setUser(currentUser);
		}
		
		return repository.save(task);
	}

	@Override
	public List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<>();
		tasks = repository.findAll();
		return new ArrayList<>(tasks);
	}

	@Override
	public void delete(Task task) {
		repository.delete(task);
	}
	
	@Override
	public List<Task> getTasksByStatus(TaskStatus status) {
		List<Task> tasks = repository.findByStatus(status);
		return tasks;
	}

}
