// src/main/java/com/techangelx/xcore/dto/TaskResponse.java

package com.techangelx.xcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private UUID id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
}
