package com.readyup.manager.definitions;

import com.readyup.domain.User;
import org.springframework.security.authentication.AuthenticationManager;

public interface AuthManager {

    Boolean existsByUsername(String username);
    void createUser(User user);
    String encodePassword(String password);
    AuthenticationManager getAuthenticationManager();
}
