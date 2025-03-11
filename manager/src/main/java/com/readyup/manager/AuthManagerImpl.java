package com.readyup.manager;
import com.readyup.manager.definitions.AuthManager;
import org.springframework.stereotype.Service;

import com.readyup.domain.User;
import com.readyup.manager.definitions.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthManagerImpl implements AuthManager {
    private final AuthenticationManager authenticationManager;
    private final UserManager userManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthManagerImpl(AuthenticationManager authenticationManager, UserManager userManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userManager.userExists(username);
    }

    @Override
    public void createUser(User person) {
        person.setPassword(encodePassword(person.getPassword()));
        userManager.createUser(person);
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }
}
