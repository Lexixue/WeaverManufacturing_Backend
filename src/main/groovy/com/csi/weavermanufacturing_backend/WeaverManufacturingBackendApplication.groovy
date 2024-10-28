package com.csi.weavermanufacturing_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.csi.weavermanufacturing_backend.entity.User;
import com.csi.weavermanufacturing_backend.repository.UserRepository;

@SpringBootApplication
class WeaverManufacturingBackendApplication {

    static void main(String[] args) {
        SpringApplication.run(WeaverManufacturingBackendApplication, args);
    }

    @Bean
    public CommandLineRunner initializeDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            if (userRepository.count() == 0) {
                User defaultUser = new User();
                defaultUser.setUserName("defaultUser");
                defaultUser.setUserPassword(passwordEncoder.encode("defaultPassword")); // Hash the password
                defaultUser.setIsAdmin(true);
                userRepository.save(defaultUser);
                System.out.println("Default user created: username='defaultUser', password='defaultPassword'");
            }
        };
    }
}
