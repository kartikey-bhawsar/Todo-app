package com.infy.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.todo.dto.TodoDTO;
import com.infy.todo.entity.Todo;
import com.infy.todo.repository.TodoRepo;

@Service
public class TodoService {

	@Autowired
	private TodoRepo todoRepo;

	public List<Todo> findAllTodos() {
		return todoRepo.findAll();
	}

	public Todo addTodo(Todo todo) {
		return todoRepo.save(todo);
	}

	public TodoDTO getTodoById(int taskId) {
		Optional<Todo> optional = todoRepo.findById(taskId);
		Todo todo = optional.orElse(new Todo());
		return todo.entityToDto();
	}

	public void deleteTodoById(int taskId) {
		todoRepo.deleteById(taskId);
	}

	public List<TodoDTO> searchTodo(String text) {
		List<Todo> todos = todoRepo.searchTodo(text);
		return todos.stream().map(Todo::entityToDto).collect(Collectors.toList());
	}
}
