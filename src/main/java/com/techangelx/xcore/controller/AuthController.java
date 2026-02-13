// src/main/java/com/techangelx/xcore/controller/AuthController.java

package com.techangelx.xcore.controller;

import com.techangelx.xcore.dto.AuthResponse;
import com.techangelx.xcore.dto.LoginRequest;
import com.techangelx.xcore.dto.RegisterRequest;
import com.techangelx.xcore.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
   
   
    }
   
    @GetMapping("/")
    public String welcome() {
        return "Xcore API is Online. Please use Postman to interact with endpoints.";
    }
}
