package com.readyup.api.endpoint;

import com.readyup.api.endpointdefinition.AuthEndpointDefinition;
import com.readyup.api.request.SignInRequest;
import com.readyup.api.request.SignUpRequest;
import com.readyup.domain.Person;
import com.readyup.manager.definitions.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthEndpoint implements AuthEndpointDefinition {

    private final AuthManager authManager;

    @Autowired
    public AuthEndpoint(AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(SignUpRequest request) {
        if (authManager.existsByUsername(request.getUsername())) {
            return new ResponseEntity<>("Username taken!", HttpStatus.BAD_REQUEST);
        }
        Person newPerson = Person.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                    .build();
        authManager.createUser(newPerson);
        return new ResponseEntity<>("User "+ newPerson.getUsername() +" created!", HttpStatus.OK);
    }

    @Override
    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(SignInRequest request) {
        Authentication authentication = authManager.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed in", HttpStatus.OK);
    }


}
