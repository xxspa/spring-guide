package com.newzhxu.application;

import com.newzhxu.application.security.MyUser;
import com.newzhxu.application.security.MyUserRepo;
import com.newzhxu.application.security.UserMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(MyUserRepo repo, PasswordEncoder passwordEncoder) {
        return args -> {
            String encodedPassword = passwordEncoder.encode("admin");
            MyUser myUser = new MyUser().setUsername("admin").setPassword(encodedPassword).setRoles(List.of("ROLE_USER", "ROLE_ADMIN"))
                    .setAccountNonExpired(true)
                    .setAccountNonLocked(true)
                    .setCredentialsNonExpired(true)
                    .setEnabled(true);
            repo.getMyUserByUsername(myUser.getUsername()).ifPresentOrElse(
                    user -> {
                        UserMapper.INSTANCE.updateUserFromMyUser(myUser, user);
                        repo.save(user);
                    },
                    () -> repo.save(myUser)
            );
        };
    }
}
