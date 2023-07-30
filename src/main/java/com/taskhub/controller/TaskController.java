package com.taskhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskhub.common.Constants;
import com.taskhub.dto.TaskTO;
import com.taskhub.model.Task;
import com.taskhub.service.TaskService;

@RestController
@RequestMapping(value = Constants.API_URL)
public class TaskController {
	
	@Autowired
	TaskService service;
	
	@RequestMapping(value = "/task/save", method = RequestMethod.GET)
	public ResponseEntity<?> saveTask(@RequestBody TaskTO taskTo) {
		
		Task task = new Task(taskTo);
		service.saveTask(task);
		
		return null;
	}
	
}
