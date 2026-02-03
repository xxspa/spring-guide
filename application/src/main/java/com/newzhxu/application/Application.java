package com.newzhxu.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.provisioning.UserDetailsManager;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserDetailsManager userDetailsService) {
        return args -> {
//            userDetailsService.createUser(new MyUser().setUsername("admin").setPassword("admin").setRoles(List.of("ROLE_USER"))
//                    .setAccountNonExpired(true)
//                    .setAccountNonLocked(true)
//                    .setCredentialsNonExpired(true)
//                    .setEnabled(true));
        };
    }
}
