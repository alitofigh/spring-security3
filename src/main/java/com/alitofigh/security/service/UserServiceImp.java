package com.alitofigh.security.service;

import com.alitofigh.security.config.JwtService;
import com.alitofigh.security.domain.Role;
import com.alitofigh.security.domain.User;
import com.alitofigh.security.repo.RoleRepository;
import com.alitofigh.security.repo.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by A_Tofigh at 2/26/2024
 */

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

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
        return userRepository.findUserByEmail(user.getUsername())
                .orElse(userRepository.save(user));
    }

    @Override
    public boolean deleteUser(User user) {
        User savedUser = userRepository.findUserByEmail(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("username { " + user.getUsername() + " } not found."));
        userRepository.delete(user);
        return true;
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
    public void addRoleToUser(User user, Role role) {
        User foundUser = userRepository.findUserByEmail(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("username { " + user.getUsername() + " } not found."));
        foundUser.getRoles().add(role);
    }
}
