package com.mohit.TaskManager.dtos;

import com.mohit.TaskManager.entity.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Builder
@Setter
@Data
public class TaskDto {

   private Long id;
    private String title;

    private String description;

    private Date dueDate;

    private String status;

    private String priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
