package com.alitofigh.security.repo;

import com.alitofigh.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by A_Tofigh at 2/24/2024
 */

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}
