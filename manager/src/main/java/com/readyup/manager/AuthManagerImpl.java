package com.readyup.manager;

import com.readyup.domain.Person;
import com.readyup.manager.definitions.AuthManager;
import com.readyup.manager.definitions.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthManagerImpl implements AuthManager {

    private final AuthenticationManager authenticationManager;
    private final PersonManager personManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthManagerImpl(AuthenticationManager authenticationManager, PersonManager personManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.personManager = personManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return personManager.personExists(username);
    }

    @Override
    public void createUser(Person person) {
        person.setPassword(encodePassword(person.getPassword()));
        personManager.createPerson(person);
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
