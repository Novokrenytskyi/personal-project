package com.novo.personalproject.service;

import com.novo.personalproject.dao.UserRepository;
import com.novo.personalproject.model.entity.Gender;
import com.novo.personalproject.model.entity.Role;
import com.novo.personalproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcIdToken oidcIdToken = userRequest.getIdToken();
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        OidcUser oidcUser = buildOidcUser(oidcIdToken, accessToken);
        return oidcUser;
    }

    private OidcUser buildOidcUser(OidcIdToken idToken, OAuth2AccessToken accessToken) {
        String email = idToken.getEmail();
        String firstName = idToken.getGivenName();
        String lastName = idToken.getFamilyName();
        String gender = idToken.getGender();
        String birthdate = idToken.getBirthdate();

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("email", email);
        attributes.put("firstName", firstName);
        attributes.put("lastName", lastName);
        attributes.put("gender", gender);
        attributes.put("birthdate", birthdate);

        Set<OidcUserAuthority> authorities = new HashSet<>();
        OidcUserAuthority authority = new OidcUserAuthority(idToken);
        authorities.add(authority);

        OidcUser oidcUser = new DefaultOidcUser(authorities, idToken);

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()) {
            User newUser = User.builder()
                    .email(oidcUser.getEmail())
                    .firstName(oidcUser.getGivenName())
                    .lastName(oidcUser.getFamilyName())
                    .gender(Gender.valueOf(oidcUser.getGender()))
                    .role(Role.USER)
                    .build();

            userRepository.save(newUser);
        }

        return oidcUser;
    }
}
