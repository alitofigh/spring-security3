package com.alitofigh.security.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by A_Tofigh at 2/26/2024
 */
@Entity
@Table(name = "sd_role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    /*@ManyToMany(mappedBy = "roles")
    private Set<User> users;*/
}
