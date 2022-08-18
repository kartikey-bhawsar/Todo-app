package com.infy.todo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.infy.todo.dto.TodoDTO;

@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int taskId;
	String taskName;
	String priority;
	@Temporal(TemporalType.DATE)
	Date targetDate;
	
	public Todo() {
		super();
	}
	public Todo(String taskName, String priority, Date targetDate) {
		super();
		this.taskName = taskName;
		this.priority = priority;
		this.targetDate = targetDate;
	}
	public Todo(int taskId, String taskName, String priority, Date targetDate) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.priority = priority;
		this.targetDate = targetDate;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	@Override
	public String toString() {
		return "Todo [taskId=" + taskId + ", taskName=" + taskName + ", priority=" + priority + ", targetDate="
				+ targetDate + "]";
	}

	public TodoDTO entityToDto() {
		TodoDTO dto = new TodoDTO();
		dto.setTaskId(this.taskId);
		dto.setTaskName(this.taskName);
		dto.setPriority(this.priority);
		dto.setTargetDate(this.targetDate);
		return dto;
	}
}
