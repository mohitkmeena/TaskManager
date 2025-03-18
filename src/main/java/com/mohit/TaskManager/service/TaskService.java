package com.mohit.TaskManager.service;

import com.mohit.TaskManager.dtos.TaskDto;
import com.mohit.TaskManager.dtos.Tasks;
import com.mohit.TaskManager.entity.Task;
import com.mohit.TaskManager.exceptions.TaskNotFoundException;
import com.mohit.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.PriorityQueue;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    private final PriorityQueue<Task> taskQueue = new PriorityQueue<>(new TaskComparator());
    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }
        return authentication.getName();
    }
    public List<TaskDto> loadTasksIntoQueue() {
        String username = getAuthenticatedUsername();
        List<Task> tasks = taskRepository.findByUser(username);
        taskQueue.clear();
        taskQueue.addAll(tasks);
        List<TaskDto>taskDtos =taskQueue.stream().map(this::taskToDto).toList();
        return taskDtos;

    }


    public TaskDto createTask(TaskDto taskDto) {
        String user=getAuthenticatedUsername();

        Task task = Task.builder()
                .status(Task.Status.fromString(taskDto.getStatus()))
                .priority(Task.Priority.fromString(taskDto.getPriority()))
                .title(taskDto.getTitle())
                .dueDate(taskDto.getDueDate())
                .description(taskDto.getDescription())
                .user(user) // Now authentication is correctly fetched
                .build();

        task = taskRepository.save(task);
        return taskToDto(task);
    }

    @Cacheable(value = "tasks", key = "#id")
    public TaskDto getTask(Long id) {
        String user=getAuthenticatedUsername();
        Task task = taskRepository.getByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        return taskToDto(task);
    }

    @CacheEvict(value = "tasks", key = "#id")
    public String deleteTask(Long id) {
        String user=getAuthenticatedUsername();
        boolean exists = taskRepository.existsByIdAndUser(id, user);
        if (exists) {
            taskRepository.deleteById(id);
            return "Task deleted successfully";
        }
        return "Task not found";
    }

    @CachePut(value = "tasks", key = "#id")
    public TaskDto taskCompleted(Long id) {
        String user=getAuthenticatedUsername();
        Task task = taskRepository.getByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        task.setStatus(Task.Status.completed);
        task = taskRepository.save(task);
        return taskToDto(task);
    }

    @CachePut(value = "tasks", key = "#id")
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        String user=getAuthenticatedUsername();
        Task task = taskRepository.getByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (taskDto.getDescription() != null) task.setDescription(taskDto.getDescription());
        if (taskDto.getTitle() != null) task.setTitle(taskDto.getTitle());
        if (taskDto.getPriority() != null) task.setPriority(Task.Priority.fromString(taskDto.getPriority()));
        if (taskDto.getStatus() != null) task.setStatus(Task.Status.fromString(taskDto.getStatus()));
        if (taskDto.getDueDate() != null) task.setDueDate(taskDto.getDueDate());

        task = taskRepository.save(task);
        return taskToDto(task);
    }

    private TaskDto taskToDto(Task task) {
        return TaskDto.builder()
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .priority(task.getPriority().toString())
                .status(task.getStatus().toString())
                .title(task.getTitle())
                .id(task.getId())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    public Tasks getAllTask(int pageNumber) {
        String user=getAuthenticatedUsername();
        int pageSize=10;
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<Task> page=taskRepository.findByUser(pageable,user);
        List<TaskDto> taskList=page.stream().map(this::taskToDto).toList();
        return new Tasks(taskList,pageNumber,pageSize,page.getTotalElements(),page.getTotalPages());



    }
    public Tasks getAllTask(int pageNumber, String priority, String status) {
        String username = getAuthenticatedUsername();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Task> page;

        if (priority != null && status != null) {
            page = taskRepository.findByUserAndPriorityAndStatus(username, Task.Priority.fromString(priority), Task.Status.fromString(status), pageable);
        } else if (priority != null) {
            page = taskRepository.findByUserAndPriority(username, Task.Priority.fromString(priority), pageable);
        } else if (status != null) {
            page = taskRepository.findByUserAndStatus(username, Task.Status.fromString(status), pageable);
        } else {
            page = taskRepository.findByUser(pageable,username);
        }

        List<TaskDto> taskList = page.stream().map(this::taskToDto).toList();
        return new Tasks(taskList, pageNumber, pageSize, page.getTotalElements(), page.getTotalPages());
    }


}
