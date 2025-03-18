package com.mohit.TaskManager.service;
import com.mohit.TaskManager.entity.Task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {

        int priorityComparison = t1.getPriority().compareTo(t2.getPriority());
        if (priorityComparison != 0) {
            return priorityComparison;
        }
        return t1.getDueDate().compareTo(t2.getDueDate());
    }
}
