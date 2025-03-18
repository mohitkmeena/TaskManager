package com.mohit.TaskManager.repository;

import com.mohit.TaskManager.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> getByIdAndUser(Long id, String user);

    boolean existsByIdAndUser(Long id, String user);

    Page<Task> findByUser(Pageable pageable,String user);
    Page<Task> findByUserAndPriorityAndStatus(String user, Task.Priority priority, Task.Status status, Pageable pageable);

    Page<Task> findByUserAndPriority(String user, Task.Priority priority, Pageable pageable);

    Page<Task> findByUserAndStatus(String user, Task.Status status, Pageable pageable);

    List<Task> findByUser(String username);
}
