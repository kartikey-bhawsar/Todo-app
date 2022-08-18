package com.infy.todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.todo.entity.Todo;
import com.infy.todo.repository.TodoRepo;
import com.infy.todo.service.TodoService;

@SpringBootTest
class TodoAppApplicationTests {

	@Mock
    TodoRepo todoRepo;

    @InjectMocks
    TodoService todoService=new TodoService();

    @Test 
    void findAllTodosVaildTest()
    {
        List<Todo> todos=new ArrayList<>();
        todos.add(new Todo(1,"Go to gym","high",new Date()));
        Mockito.when(todoRepo.findAll()).thenReturn(todos);
        Assertions.assertEquals(todos.size(), todoService.findAllTodos().size());
    }
    @Test 
    void addTodoValidTest() throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date date=dateFormat.parse("15/08/2022");
        Todo todo=new Todo("Go to gym","high",date);
        Mockito.when(todoRepo.save(todo)).thenReturn(todo);
        Assertions.assertEquals(todo,todoService.addTodo(todo));
        }

    @Test 
    void getTodoByIdValidTestCase()
    {
        int taskId=1;
        Todo todo=new Todo(1,"Go to gym","high",new Date());
        Optional<Todo> optional=Optional.of(todo);
        Mockito.when(todoRepo.findById(taskId)).thenReturn(optional);
        Assertions.assertEquals(todo.entityToDto().toString(), todoService.getTodoById(taskId).toString());

    } 
    @Test 
    void searchTodovalidTest()
    {
        String text="home";
        List<Todo> todos=new ArrayList<>();
        todos.add(new Todo(1,"Go to gym","high",new Date()));
        todos.add(new Todo(2,"Go to classes","low",new Date()));
        Mockito.when(todoRepo.searchTodo(text)).thenReturn(todos);
        Assertions.assertEquals(todos.size(),todoService.searchTodo(text).size());

    } 

}
