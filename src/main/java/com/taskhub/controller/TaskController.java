package com.taskhub.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskhub.common.Constants;
import com.taskhub.common.TaskStatus;
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
	public ResponseEntity<?> saveOrUpdateTask(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, @RequestBody TaskTO taskTo) {
		Task task = service.saveOrUpdateTask(taskTo);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

	@RequestMapping(value = { "/tasks/{status}", "/tasks/" }, method = RequestMethod.GET)
	public ResponseEntity<?> getAllTasks(HttpServletRequest request, @PathVariable String status, @RequestParam Long id) {
		Map<String, List<Task>> json = new HashMap<>();
		List<Task> tasks = new ArrayList<>();
		User user = userService.findById(id);
		System.out.println("user :::"+user);
		if (status.equals("all")) {
			tasks = service.getAllByUser(user);
		} else {
			TaskStatus status2 = Enum.valueOf(TaskStatus.class, status.toUpperCase());
			tasks = service.getTasksByUserAndStatus(status2, user);
		}
		json.put("tasks", tasks);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public ResponseEntity<?> getTask(@RequestParam Long id) {
		Task task = service.getTaskById(id);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deleteTask(@RequestParam Long id) {
		Task task = service.getTaskById(id);
		service.delete(task);
	}

}
