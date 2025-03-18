package com.mohit.TaskManager.controller;

import com.mohit.TaskManager.dtos.TaskDto;
import com.mohit.TaskManager.dtos.Tasks;
import com.mohit.TaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks/")
public class TaskCotroller {
   @Autowired private TaskService taskService;

    @PostMapping("/")
    public TaskDto createATask(@RequestBody TaskDto taskDto){
       return taskService.createTask(taskDto);
    }

    @GetMapping("/")
    public Tasks getAllTask(@RequestParam int pageNum){
        return taskService.getAllTask(pageNum);
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable(name = "id") Long id){
        return taskService.getTask(id);
    }

    @PutMapping("/{id}")
    public TaskDto getTask(@PathVariable(name = "id")  Long id,@RequestBody TaskDto taskDto){
        return taskService.updateTask(id,taskDto);
    }

    @PutMapping("task-completed/{id}")
    public String completeTask(@PathVariable(name = "id") Long id){
        taskService.taskCompleted(id);
        return "task completed";
    }

    @GetMapping("filter/")
    public Tasks getAllTasksFileter(@RequestParam int pageNum,@RequestParam String priority,@RequestParam String status){
        return taskService.getAllTask(pageNum,priority,status);
    }
    @GetMapping("/sort")
    public List<TaskDto> getSortTask(){
          return taskService.loadTasksIntoQueue();
    }

    @DeleteMapping("/{id}")
    public  String deleteTask(@PathVariable(name = "id") Long id){
        taskService.deleteTask(id);
        return "task deleted successfully";
    }




}
