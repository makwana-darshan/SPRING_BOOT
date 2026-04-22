package com.jsp.service;

import org.aspectj.weaver.reflect.ArgNameFinder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.entity.User;
import com.jsp.repository.UserRepository;

@Component
public class AdminUserInializer {
	@Bean
	public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setRole("ROLE_ADMIN");

				userRepository.save(admin);
				System.out.println("admin created..!");
			}
		};
	}

}
