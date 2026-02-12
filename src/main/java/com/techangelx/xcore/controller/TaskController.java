// src/main/java/com/techangelx/xcore/controller/TaskController.java

package com.techangelx.xcore.controller;

import com.techangelx.xcore.dto.TaskRequest;
import com.techangelx.xcore.dto.TaskResponse;
import com.techangelx.xcore.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request,
            Authentication authentication) {
        
        String email = authentication.getName();
        TaskResponse response = taskService.createTask(request, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getMyTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        
        String email = authentication.getName();
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskResponse> tasks = taskService.getMyTasks(email, pageable);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable UUID id,
            @Valid @RequestBody TaskRequest request,
            Authentication authentication) {
        
        String email = authentication.getName();
        TaskResponse response = taskService.updateTask(id, request, email);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponse> markComplete(
            @PathVariable UUID id,
            Authentication authentication) {
        
        String email = authentication.getName();
        TaskResponse response = taskService.markComplete(id, email);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable UUID id,
            Authentication authentication) {
        
        String email = authentication.getName();
        taskService.deleteTask(id, email);
        return ResponseEntity.noContent().build();
    }
}
