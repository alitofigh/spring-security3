package com.alitofigh.security.controller;

import com.alitofigh.security.service.UserServiceImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;


/**
 * Created by A_Tofigh at 2/26/2024
 */

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserServiceImp userServiceImp;

    @PostMapping("authenticate")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest request) {
        String token = userServiceImp.authenticate(request.username, request.password);
        return ResponseEntity.ok(token);
    }
}

@Data
class AuthRequest {
    String username;
    String password;
}
