package com.infy.todo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infy.todo.dto.TodoDTO;
import com.infy.todo.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping({ "/list-todos", "/" })
	public String showTodos(ModelMap model) {
		model.addAttribute("todos", todoService.findAllTodos());
		return "list-todos";
	}

	@GetMapping("/add-todo")
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new TodoDTO());
		return "add-todo";
	}

	@PostMapping("/add-todo")
	public String addTodo(ModelMap model, TodoDTO todoDto) {
		todoService.addTodo(todoDto.dtoToEntity());
		return "redirect:/list-todos";
	}

	@GetMapping("/update-todo")
	public String updateTodo(ModelMap model, @RequestParam int id) {
		TodoDTO todoDto = todoService.getTodoById(id);
		model.addAttribute("todo", todoDto);
		return "add-todo";
	}

	@GetMapping("/delete-todo")
	public String deleteTodo(ModelMap model, @RequestParam int id) {
		todoService.deleteTodoById(id);
		return "redirect:/list-todos";
	}

	@PostMapping("/search-todo")
	public String searchTodo(ModelMap model, @RequestParam String text) {
		List<TodoDTO> dtos = todoService.searchTodo(text);
		model.addAttribute("dtos", dtos);
		return "search-todo";
	}

}
