package com.alitofigh.security;

import com.alitofigh.security.domain.Role;
import com.alitofigh.security.domain.User;
import com.alitofigh.security.repo.RoleRepository;
import com.alitofigh.security.repo.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository, PasswordEncoder PasswordEncoder, RoleRepository roleRepository) {
		return args -> {
			Role role = Role.builder()
					.name("ROLE_USER")
					.build();
			//roleRepository.save(role);
			User user = User.builder()
					.firstname("ali")
					.lastname("tofigh")
					.email("ali@gmail.com")
					.password(PasswordEncoder.encode("123"))
					.roles(Set.of(role))
					.build();
			userRepository.save(user);
		};
	}

}
