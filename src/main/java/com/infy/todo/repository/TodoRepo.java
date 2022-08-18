package com.infy.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infy.todo.entity.Todo;

public interface TodoRepo extends JpaRepository<Todo, Integer> {

	@Query("select t from Todo t where t.taskName LIKE %?1% or t.priority LIKE %?1% or t.targetDate LIKE %?1%")
	List<Todo> searchTodo(String text);

}
