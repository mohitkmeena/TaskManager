package com.mohit.TaskManager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

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
    @NotBlank(message = "please give a valid title")
    private String title;
    @NotBlank(message = "please give a valid description")
    private String description;

    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private String user;

    public enum Status {
        pending,
        completed;
        public static Status fromString(String value){
            return Status.valueOf(value.toLowerCase());
        }
    }
    public enum Priority{
        high,
        low,
        medium;
        public  static Priority fromString(String value){
            return Priority.valueOf(value.toLowerCase());
        }
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
