package com.examly.springapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TaskController {
	
	@Autowired
	TaskRepo repo;
	
	@RequestMapping("/saveTask")
	@ResponseBody
	public Optional<Task> addTask(@RequestParam int id,String name,String date,String taskName)
	{
		Task task = new Task();
		task.setTaskId(id);
		task.setTaskHolderName(name);
		task.setTaskDate(date);
		task.setTaskName(taskName);
		task.setTaskStatus("In Progess");
		repo.save(task);
		
		return repo.findById(id);
	}
	
	@RequestMapping("/changeStatus")
	@ResponseBody
	public Optional<Task> updateSeries(@RequestParam int id)
	{
		repo.updateTaskStatus(id);
		return repo.findById(id);
	}
	
	@RequestMapping("/deleteTask")
	@ResponseBody
	public Optional<Task> deleteTasks(@RequestParam int id)
	{
		repo.deleteById(id);
		return repo.findById(id);
	}
	
	@RequestMapping("/allTasks")
	@ResponseBody
	public List<Task> allTask()
	{
		return repo.findAll();
	}
	
	@RequestMapping("/getTask")
	public ResponseEntity<Optional<Task>> getTaskName(@RequestParam String name)
	{
		return new ResponseEntity<Optional<Task>>(repo.findBytaskHolderNameLike("%"+name+"%"),HttpStatus.OK);
	}

}