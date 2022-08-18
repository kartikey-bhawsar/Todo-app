package com.infy.todo.dto;

import java.util.Date;

import com.infy.todo.entity.Todo;

public class TodoDTO {
	int taskId;
	String taskName;
	String priority="high";
	Date targetDate=new Date();
	
	public TodoDTO() {
		super();
	}

	public TodoDTO(String taskName, String priority, Date targetDate) {
		super();
		this.taskName = taskName;
		this.priority = priority;
		this.targetDate = targetDate;
	}

	public TodoDTO(int taskId, String taskName, String priority, Date targetDate) {
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
		return "TodoDTO [taskId=" + taskId + ", taskName=" + taskName + ", priority=" + priority + ", targetDate="
				+ targetDate + "]";
	}

	public Todo dtoToEntity() {
		Todo entity = new Todo();
		entity.setTaskId(this.taskId);
		entity.setTaskName(this.taskName);
		entity.setPriority(this.priority);
		entity.setTargetDate(this.targetDate);
		return entity;
	}
}
