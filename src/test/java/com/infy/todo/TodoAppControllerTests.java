package com.infy.todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.todo.controller.TodoController;
import com.infy.todo.dto.TodoDTO;
import com.infy.todo.entity.Todo;
import com.infy.todo.service.TodoService;

@WebMvcTest(TodoController.class)
public class TodoAppControllerTests {

	@MockBean
	private TodoService todoService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void showTodosValidTest() throws Exception
	{
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
		Date date=dateFormat.parse("20/08/2022");
		List<Todo> todos=new ArrayList<>();
		todos.add(new Todo(1,"Go to gym","high",date));
		todos.add(new Todo(2,"Playing games","low",date));
		Mockito.when(todoService.findAllTodos()).thenReturn(todos);
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/list-todos"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("list-todos"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("todos"));
	}

	@Test
	void showAddTodoPageValidTest() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/add-todo"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("add-todo"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("todo"));
	}

	@Test
	void showUpdateTodoPageValidTest() throws Exception {
		String id = "1";
		Todo todo = new Todo(1,"Go to gym", "high", new Date());
		Mockito.when(todoService.getTodoById(1)).thenReturn(todo.entityToDto());
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/update-todo")
			.param("id", id))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("add-todo"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("todo"));
	}
	
	@Test
	void searchTodoValidTest() throws Exception
	{
		String text="to";
		List<TodoDTO> todos=new ArrayList<>();
		todos.add(new TodoDTO(1,"Go to gym","high",new Date()));
		todos.add(new TodoDTO(2,"Playing games","low",new Date()));
		todos.add(new TodoDTO(3,"Go to school","high",new Date()));
		
		List<TodoDTO> todos2=new ArrayList<>();
		todos.add(new TodoDTO(1,"Go to gym","high",new Date()));
		todos.add(new TodoDTO(3,"Go to school","high",new Date()));
		
		Mockito.when(todoService.searchTodo(text)).thenReturn(todos2);
		
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/search-todo")
			.param("text", text))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("search-todo"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("dtos"))
			.andExpect(MockMvcResultMatchers.model().attribute("dtos",todos2));
	}
	
	@Test
	void addTodoValidTest() throws Exception
	{
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
		Date date=dateFormat.parse("20/08/2022");
		TodoDTO todoDto=new TodoDTO(1,"Go to gym","high",date);
		Mockito.when(todoService.addTodo(Mockito.any(Todo.class))).thenReturn(null);
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/add-todo")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(todoDto)))
			.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.view().name("redirect:/list-todos"));
	}
	
	@Test
	void deleteTodoValidTest() throws Exception
	{
		String id="1";
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/delete-todo")
		.param("id",id))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/list-todos"));
	}

}
