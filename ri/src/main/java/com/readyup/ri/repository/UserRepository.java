package com.readyup.ri.repository;

import com.azure.cosmos.models.PartitionKey;
import com.readyup.ri.entity.UserEntity;
import com.readyup.ri.repository.jpa.UserRepositoryJpa;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private final UserRepositoryJpa userRepositoryJpa;

    public UserRepository(UserRepositoryJpa personRepositoryJpa) {
        this.userRepositoryJpa = personRepositoryJpa;
    }

    //Expensive operation, do not do lightly
    public Iterable<UserEntity> findAllUsers() {
        return userRepositoryJpa.findAll();
    }

    public Optional<UserEntity> findUser(String username) {
        return userRepositoryJpa.findByUsername(username).stream().findFirst();
    }
    public UserEntity createUser(UserEntity user) {
        return userRepositoryJpa.save(user);
    }

    public List<UserEntity> searchUsername(String username) {
        return userRepositoryJpa.searchUsername(username);
    }
}
