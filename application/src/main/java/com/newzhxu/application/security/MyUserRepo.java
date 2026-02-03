package com.newzhxu.application.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepo extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> getMyUserByUsername(String username);

    void deleteMyUserByUsername(String username);
}
