package com.mohit.TaskManager.dtos;
import com.mohit.TaskManager.entity.Task;
import java.util.List;

public record Tasks(List<TaskDto> tasks ,  int pageNumber,int pageSize, long totalElement,int totalPage) {
}
