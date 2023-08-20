package com.taskhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskhub.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	Task getTaskById(Long id);
}
