// src/main/java/com/techangelx/xcore/dto/AuthResponse.java

package com.techangelx.xcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
    private String role;
}
