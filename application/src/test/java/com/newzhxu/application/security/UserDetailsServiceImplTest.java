package com.newzhxu.application.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

@DataJpaTest
@Import({UserDetailsServiceImpl.class})
class UserDetailsServiceImplTest {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @MockitoBean
    AuthenticationManager authenticationManager;
    @MockitoBean
    PasswordEncoder passwordEncoder;
    @Autowired
    MyUserRepo myUserRepo;
    @Autowired
    private TestEntityManager entityManager;
    MyUser originalUser;

    @BeforeEach
    void setUp() {
        originalUser = new MyUser()
                .setUsername("testuser")
                .setPassword("password")
                .setRoles(List.of("ROLE_USER"))
                .setAccountNonExpired(true)
                .setAccountNonLocked(true)
                .setCredentialsNonExpired(true)
                .setEnabled(true);
    }

    @Test
    void updatePassword() {
        String newpassword = "newpassword";
        String encodedOldPassword = "encodedOldPassword";
        Mockito.when(passwordEncoder.encode(Mockito.eq(newpassword))).thenReturn(encodedOldPassword);
        myUserRepo.save(originalUser);
        entityManager.flush();
        entityManager.clear();

        UserDetails userDetails = userDetailsService.updatePassword(originalUser, newpassword);
        Assertions.assertThat(userDetails.getPassword()).isEqualTo(encodedOldPassword);

    }

    @Test
    void loadUserByUsername() {
        myUserRepo.save(originalUser);
        entityManager.flush();
        entityManager.clear();
        UserDetails userDetails = userDetailsService.loadUserByUsername(originalUser.getUsername());
        Assertions.assertThat(userDetails).usingRecursiveComparison().isEqualTo(originalUser);
    }

    @Test
    void createUser() {
        userDetailsService.createUser(originalUser);

        entityManager.flush();
        entityManager.clear();
        MyUser myUser = myUserRepo.getMyUserByUsername(originalUser.getUsername()).orElse(null);
        Assertions.assertThat(myUser)
                .usingRecursiveComparison()
                .ignoringFields("id") // ID 是数据库生成的，通常需要忽略
                .isEqualTo(originalUser);
//        Assertions.assertEquals(a, myUser);
    }

    @Test
    void updateUser() {
        myUserRepo.save(originalUser);
        entityManager.flush();
        entityManager.clear();
        List<String> roleAdmin = List.of("ROLE_ADMIN");
        userDetailsService.updateUser(originalUser.setRoles(roleAdmin));
        entityManager.flush();
        entityManager.clear();
        MyUser myUser = myUserRepo.getMyUserByUsername(originalUser.getUsername()).orElse(null);
//        myUser.setRoles(new ArrayList<>(myUser.getRoles()));
        Assertions.assertThat(myUser)
                .usingRecursiveComparison()
                .ignoringFields("id") // ID 是数据库生成的，通常需要忽略
                .ignoringFields("version")
//                .ignoringCollectionOrder() // 加上这一行，忽略集合顺序和具体的 List 实现类
                .isEqualTo(originalUser);
        ;

    }

    @Test
    void deleteUser() {
        myUserRepo.save(originalUser);
        entityManager.flush();
        entityManager.clear();
        userDetailsService.deleteUser(originalUser.getUsername());
        entityManager.flush();
        entityManager.clear();
        MyUser myUser = myUserRepo.getMyUserByUsername(originalUser.getUsername()).orElse(null);
        Assertions.assertThat(myUser).isNull();
    }

    @Test
    void userExists() {
        myUserRepo.save(originalUser);
        entityManager.flush();
        entityManager.clear();
        boolean exists = userDetailsService.userExists(originalUser.getUsername());
        Assertions.assertThat(exists).isTrue();
    }

    @Test
    @WithMockUser(username = "testuser")
    void changePassword() {
        myUserRepo.save(originalUser);
        entityManager.flush();
        entityManager.clear();

        String newpassword = "newpassword";
        String encodedOldPassword = "encodedOldPassword";
        Mockito.when(passwordEncoder.encode(Mockito.eq(newpassword))).thenReturn(encodedOldPassword);

        userDetailsService.changePassword(originalUser.getPassword(), "newpassword");
        entityManager.flush();
        entityManager.clear();
        MyUser myUser = myUserRepo.getMyUserByUsername(originalUser.getUsername()).orElse(null);
        Assertions.assertThat(myUser).isNotNull();
        Assertions.assertThat(myUser.getPassword()).isEqualTo(encodedOldPassword);
    }
}