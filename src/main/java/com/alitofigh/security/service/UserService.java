package com.alitofigh.security.service;

import com.alitofigh.security.domain.Role;
import com.alitofigh.security.domain.User;

import java.util.List;
import java.util.Set;

/**
 * Created by A_Tofigh at 2/26/2024
 */

public interface UserService {

    String authenticate(String username, String password);

    String generateToken(String username);

    boolean validateToken(String token);

    User addUser(User user);

    void deleteUserById(Integer id);

    boolean deleteUser(User user);

    void addRole(Role role);

    void deleteRole(Role role);

    void addRoleToUser(String user, Set<Role> role);

    List<User> getUsers();

    User getUser(String username);
}
