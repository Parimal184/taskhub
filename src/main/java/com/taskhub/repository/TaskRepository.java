package com.taskhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskhub.common.TaskStatus;
import com.taskhub.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	List<Task> findByStatus(TaskStatus status);
}
