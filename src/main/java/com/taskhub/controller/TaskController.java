package com.taskhub.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskhub.common.Constants;
import com.taskhub.dto.TaskTO;
import com.taskhub.model.Task;
import com.taskhub.model.User;
import com.taskhub.service.TaskService;
import com.taskhub.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = Constants.API_URL)
@CrossOrigin("http://localhost:4200")
public class TaskController {
	
	@Autowired
	TaskService service;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/task/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveTask(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody TaskTO taskTo) {
		
		User currentUser = userService.findByEmail(taskTo.getCreatorEmail());
		Task task = new Task(taskTo);
		task.setUser(currentUser);
		System.out.println("Task :"+ task);
		service.saveTask(task);
		return new ResponseEntity<>(taskTo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/task/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateTask(@RequestBody TaskTO taskTo) {
		Task task = new Task(taskTo);
		service.updateTask(task);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/task/all", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTasks() {
		Map<String, List<Task>> json = new HashMap<>();
		List<Task> tasks = service.getAllTasks();
		json.put("tasks", tasks);
		System.out.println("tasks ::"+tasks);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public ResponseEntity<?> getTask(@RequestParam Long id) {
		Task task = service.getTaskById(id);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
}
