package com.alitofigh.security.controller;

import com.alitofigh.security.service.UserServiceImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by A_Tofigh at 2/26/2024
 */

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserServiceImp userServiceImp;

    @PostMapping("authenticate")
    public ResponseEntity<Map<String, String>> getToken(@RequestBody AuthRequest request) {
        String token = userServiceImp.authenticate(request.username, request.password);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResponseEntity.ok(map);
    }
}

@Data
class AuthRequest {
    String username;
    String password;
}

@Data
class AuthResponse {
    String token;
}
