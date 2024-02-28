package com.alitofigh.security.repo;

import com.alitofigh.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by A_Tofigh at 2/26/2024
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
}
