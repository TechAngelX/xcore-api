// src/main/java/com/techangelx/xcore/service/TaskService.java

package com.techangelx.xcore.service;

import com.techangelx.xcore.dto.TaskRequest;
import com.techangelx.xcore.dto.TaskResponse;
import com.techangelx.xcore.entity.Task;
import com.techangelx.xcore.entity.User;
import com.techangelx.xcore.repository.TaskRepository;
import com.techangelx.xcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskResponse createTask(TaskRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(Task.Status.TODO);
        task.setUser(user);

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    public Page<TaskResponse> getMyTasks(String email, Pageable pageable) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Page<Task> tasks = taskRepository.findByUser(user, pageable);
        return tasks.map(this::mapToResponse);
    }

    public TaskResponse updateTask(UUID taskId, TaskRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can only update your own tasks");
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    public TaskResponse markComplete(UUID taskId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can only update your own tasks");
        }

        task.setStatus(Task.Status.DONE);
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    public void deleteTask(UUID taskId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can only delete your own tasks");
        }

        taskRepository.delete(task);
    }

    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name(),
                task.getCreatedAt()
        );
    }
}
