package com.taskhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taskhub.common.TaskStatus;
import com.taskhub.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	@Query("SELECT t from Task t order by t.updated desc")
	List<Task> findAll();
	
	@Query("SELECT t from Task t where t.status like :status order by t.updated desc")
	List<Task> findByStatus(TaskStatus status);
}
