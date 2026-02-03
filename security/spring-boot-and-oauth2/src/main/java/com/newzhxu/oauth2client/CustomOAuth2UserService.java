package com.newzhxu.oauth2client;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

/**
 * 自定义 OAuth2UserService，用于验证 GitHub 用户是否属于特定组织
 * 
 * 使用方法：在 OAuth2ClientApplication 中添加以下 Bean（取消注释）：
 * 
 * @Bean
 * public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(WebClient rest) {
 *     return new CustomOAuth2UserService(rest, "your-organization-name");
 * }
 * 
 * 注意：将 "your-organization-name" 替换为你的 GitHub 组织名称
 */
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    
    private final WebClient webClient;
    private final String requiredOrganization;
    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    /**
     * @param webClient WebClient 实例，用于调用 GitHub API
     * @param requiredOrganization 要求用户所属的 GitHub 组织名称
     */
    public CustomOAuth2UserService(WebClient webClient, String requiredOrganization) {
        this.webClient = webClient;
        this.requiredOrganization = requiredOrganization;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User user = delegate.loadUser(request);
        
        // 只对 GitHub 提供商进行组织验证
        if (!"github".equals(request.getClientRegistration().getRegistrationId())) {
            return user;
        }

        OAuth2AuthorizedClient client = new OAuth2AuthorizedClient(
                request.getClientRegistration(), 
                user.getName(), 
                request.getAccessToken()
        );
        
        String url = user.getAttribute("organizations_url");
        List<Map<String, Object>> orgs = webClient
                .get().uri(url)
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(List.class)
                .block();

        if (orgs != null && orgs.stream().anyMatch(org -> requiredOrganization.equals(org.get("login")))) {
            return user;
        }

        throw new OAuth2AuthenticationException(
                new OAuth2Error("invalid_token", "Not in required organization: " + requiredOrganization, "")
        );
    }
}
