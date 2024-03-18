package com.alitofigh.security.service;

import com.alitofigh.security.config.JwtService;
import com.alitofigh.security.domain.Role;
import com.alitofigh.security.domain.User;
import com.alitofigh.security.repo.RoleRepository;
import com.alitofigh.security.repo.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by A_Tofigh at 2/26/2024
 */

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return generateToken(username);
    }

    @Override
    public String generateToken(String username) {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("username { " + username + " } not found."));
        Map<String, Object> extraClaims = new HashMap<>();
         extraClaims.put(
                 "roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return jwtService.generateToken(extraClaims, user);
    }

    @Override
    public boolean validateToken(String token) {
        String username = jwtService.extractClaim(token, Claims::getSubject);
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("username { " + username + " } not found."));
        return jwtService.isTokenValid(token, user);
    }

    @Override
    public User addUser(User user) {
        Optional<User> addedUser = userRepository.findUserByEmail(user.getUsername());
        if (addedUser.isPresent())
            throw new DuplicateRequestException("user with this username already exist");
        for (Role r:user.getRoles()) {
            Role savedRole = roleRepository.findRoleByName(r.getName());
            if (savedRole != null)
                r.setId(savedRole.getId());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    @Override
    public boolean deleteUser(User user) {
        User savedUser = userRepository.findUserByEmail(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("username { " + user.getUsername() + " } not found."));

        savedUser.removeRole();
        userRepository.save(savedUser);
        userRepository.delete(savedUser);
        return true;
    }

    @Override
    public void deleteUserById(Integer id) {
        User savedUser = userRepository.findUserById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user with id: { " + id + " } not found."));
        userRepository.deleteUserById(savedUser.getId());
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void addRoleToUser(String username, Set<Role> roles) {
        User foundUser = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("username { " + username + " } not found."));
        for (Role r:roles) {
            Role savedRole = roleRepository.findRoleByName(r.getName());
            if (savedRole != null)
                r.setId(savedRole.getId());
        }
        foundUser.setRoles(roles);
        userRepository.save(foundUser);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("username { " + username + " } not found."));
    }
}
