package com.newzhxu.application.security;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Locale;

@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService, UserDetailsPasswordService, UserDetailsManager {
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final MyUserRepo myUserRepo;
    private final AuthenticationManager authenticationManager;
    private final UserCache userCache = new NullUserCache();
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(MyUserRepo myUserRepo, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.myUserRepo = myUserRepo;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    private MyUser assertMyUser(UserDetails user) {
        if (user instanceof MyUser myUser) {
            return myUser;
        } else {
            throw new UserException("UserDetails is not instance of MyUser");

        }
    }

    private void validateUserDetails(UserDetails user) {
        Assert.hasText(user.getUsername(), "Username may not be empty or null");
        this.validateAuthorities(user.getAuthorities());
    }

    private void validateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Authorities list must not be null");

        for (GrantedAuthority authority : authorities) {
            Assert.notNull(authority, "Authorities list contains a null entry");
            Assert.hasText(authority.getAuthority(), "getAuthority() method must return a non-empty string");
        }

    }

    @Override
    @Transactional
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        if (!(user instanceof MyUser myUser)) {
            throw new UserException("UserDetails is not instance of MyUser");
        }
        Assert.notNull(user, "User must not be null");
        MyUser myUserwithNewPasswd = myUserRepo.getMyUserByUsername(user.getUsername().toLowerCase(Locale.ROOT))
                .map(e -> {
                    String encode = passwordEncoder.encode(newPassword);
                    e.setPassword(encode);
                    return e;
                }).orElseThrow(() -> new UsernameNotFoundException(user.getUsername()));
        myUserRepo.save(myUserwithNewPasswd);

        return myUserwithNewPasswd;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return myUserRepo.getMyUserByUsername(username.toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    @Transactional
    public void createUser(UserDetails user) {
        validateUserDetails(user);
        MyUser myUser = assertMyUser(user);
        myUserRepo.save(myUser);

    }

    @Override
    public void updateUser(UserDetails user) {
        validateUserDetails(user);
        MyUser myUser = assertMyUser(user);
        myUserRepo.save(myUser);

    }

    @Override
    public void deleteUser(String username) {
        myUserRepo.deleteMyUserByUsername(username.toLowerCase(Locale.ROOT));

    }


    @Override
    public boolean userExists(String username) {

        return myUserRepo.getMyUserByUsername(username.toLowerCase(Locale.ROOT)).isPresent();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // 获取当前认证信息
        Authentication currentUser = securityContextHolderStrategy.getContext().getAuthentication();
        if (currentUser == null) {
            throw new AccessDeniedException("未找到当前登录用户的认证信息，无法修改密码。");
        }

        String username = currentUser.getName();

        // 2. 校验旧密码（如果配置了 manager）
        if (this.authenticationManager != null) {
            log.debug("正在为用户 '{}' 重新认证以修改密码。", username);
            this.authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(username, oldPassword)
            );
        }

        // 3. 核心逻辑优化：加载 -> 修改 -> 保存
        log.debug("正在更新用户 '{}' 的密码", username);

        // 假设你的 loadUserByUsername 返回的是 MyUser 实体
        UserDetails userDetails = loadUserByUsername(username);

        if (userDetails instanceof MyUser myUser) {
            // 关键：必须使用 PasswordEncoder 对新密码加密后再存入实体
            // 假设你注入了 PasswordEncoder passwordEncoder;
            myUser.setPassword(passwordEncoder.encode(newPassword));
            myUserRepo.save(myUser);
        } else {
            throw new IllegalStateException("不支持的用户实体类型: " + userDetails.getClass().getName());
        }

        // 4. 更新 Security Context，防止 Session 失效
        updateSecurityContext(currentUser, userDetails);

        // 5. 清理缓存
        this.userCache.removeUserFromCache(username);
    }

    private void updateSecurityContext(Authentication currentAuth, UserDetails updatedUser) {
        // 使用新的用户信息创建已认证的 Token
        UsernamePasswordAuthenticationToken newAuth = UsernamePasswordAuthenticationToken.authenticated(
                updatedUser,
                null, // 密码设为 null，安全起见不保留凭证
                updatedUser.getAuthorities()
        );
        newAuth.setDetails(currentAuth.getDetails());

        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(newAuth);
        this.securityContextHolderStrategy.setContext(context);
    }
}
