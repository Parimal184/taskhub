package com.taskhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taskhub.common.TaskStatus;
import com.taskhub.model.Task;
import com.taskhub.model.User;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	@Query("SELECT t from Task t where t.user like :user order by t.updated desc")
	List<Task> findByUser(User user);
	
	@Query("SELECT t from Task t order by t.updated desc")
	List<Task> findAll();
	
	@Query("SELECT t from Task t where t.status like :status order by t.updated desc")
	List<Task> findByStatus(TaskStatus status);
	
	@Query("SELECT t from Task t where (t.status like :status AND t.user like :user) order by t.updated desc")
	List<Task> findByUserAndStatus(TaskStatus status, User user);
}
