package com.readyup.security;

import com.readyup.ri.entity.UserEntity;
import com.readyup.ri.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity person = userRepository.findUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        //TODO: create roles for people
        return new User(person.getUsername(), person.getPassword(), List.of(new SimpleGrantedAuthority("user")));
    }
}
