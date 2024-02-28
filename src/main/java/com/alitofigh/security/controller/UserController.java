package com.alitofigh.security.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by A_Tofigh at 2/26/2024
 */

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    @GetMapping("ls")
    public ResponseEntity<String> listUsers(){
        return ResponseEntity.ok().body("this is a message to show that this service was called successfully called.");
    }
}
