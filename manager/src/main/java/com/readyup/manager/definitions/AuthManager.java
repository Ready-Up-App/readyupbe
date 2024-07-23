package com.readyup.manager.definitions;

import com.readyup.domain.Person;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface AuthManager {

    Boolean existsByUsername(String username);
    void createUser(Person person);
    String encodePassword(String password);
    AuthenticationManager getAuthenticationManager();
}
