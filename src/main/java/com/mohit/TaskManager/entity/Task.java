package com.mohit.TaskManager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private String description;

    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public enum Status {
        pending,
        completed;
    }
    public enum Priority{
        high,
        low,
        medium;
    }
    @PrePersist
    public  void setCreatedTime(){
        this.createdAt=LocalDateTime.now();
    }
    @PreUpdate
    public  void setUpdatedTime(){
        this.updatedAt=LocalDateTime.now();
    }
}
