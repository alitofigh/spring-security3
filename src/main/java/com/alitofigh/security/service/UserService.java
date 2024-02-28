package com.alitofigh.security.service;

import com.alitofigh.security.domain.Role;
import com.alitofigh.security.domain.User;

/**
 * Created by A_Tofigh at 2/26/2024
 */

public interface UserService {

    String authenticate(String username, String password);

    String generateToken(String username);

    boolean validateToken(String token);

    User addUser(User user);

    boolean deleteUser(User user);

    void addRole(Role role);

    void deleteRole(Role role);

    void addRoleToUser(User user, Role role);
}
