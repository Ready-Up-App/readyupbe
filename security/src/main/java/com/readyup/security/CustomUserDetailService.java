package com.readyup.security;

import com.readyup.ri.entity.PersonEntity;
import com.readyup.ri.repository.PersonRepository;
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

    private final PersonRepository personRepository;

    @Autowired
    public CustomUserDetailService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonEntity person = personRepository.findPerson(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        //TODO: create roles for people
        return new User(person.getUsername(), person.getPassword(), List.of(new SimpleGrantedAuthority("user")));
    }
}
