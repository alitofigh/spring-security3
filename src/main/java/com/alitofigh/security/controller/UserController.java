package com.alitofigh.security.controller;

import com.alitofigh.security.domain.Role;
import com.alitofigh.security.domain.User;
import com.alitofigh.security.service.UserServiceImp;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by A_Tofigh at 2/26/2024
 */

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserServiceImp userService;
    private final ModelMapper modelMapper;

    @GetMapping("ls")
    public ResponseEntity<List<userDTOResponse>> listUsers() {
        //return ResponseEntity.ok().body(userService.getUsers());
        List<userDTOResponse> result = new ArrayList<>();
        userService.getUsers().forEach(user -> {
            result.add(modelMapper.map(user, userDTOResponse.class));
        });
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("add-user")
    public ResponseEntity<userDTOResponse> addUser(@RequestBody User user) {
        User addUser = userService.addUser(user);
        userDTOResponse response = modelMapper.map(addUser, userDTOResponse.class);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("add-role-to-user")
    public ResponseEntity<String> addRoleToUser(@RequestBody AddRoleToUserRequest request) {
        userService.addRoleToUser(request.getUsername(), request.getRoles());
        return ResponseEntity.ok("roles added to the user successfully.");
    }

    @PostMapping("delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        boolean result = userService.deleteUser(user);
        return ResponseEntity.ok(result ? "the user was deleted successfully":"there is no user to delete");
    }

    @PostMapping("delete-user-by-id")
    public ResponseEntity<String> deleteUserById(@RequestParam int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("the user was deleted successfully");
    }

    /*@ExceptionHandler(ExpiredJwtException.class)
    public void handleExceptionTest(){
        System.out.println("this method was called");
    }*/
}

@Data
class AddRoleToUserRequest {
    String username;
    Set<Role> roles;
}

@Data
class userDTOResponse {
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    @Nullable
    public Set<Role> roles;
    public boolean enabled;
    public String username;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private Set<GrantedAuthority> authorities;
}
