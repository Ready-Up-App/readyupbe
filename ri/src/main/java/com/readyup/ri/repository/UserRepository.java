package com.readyup.ri.repository;

import com.azure.cosmos.models.PartitionKey;
import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.UserEntity;
import com.readyup.ri.entity.UserGroupEntity;
import com.readyup.ri.repository.jpa.UserRepositoryJpa;
import org.javatuples.Pair;
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

    private static String USER_NOT_FOUND = "User not found";

    private final UserRepositoryJpa userRepositoryJpa;

    public UserRepository(UserRepositoryJpa personRepositoryJpa) {
        this.userRepositoryJpa = personRepositoryJpa;
    }

    //wrapper for JPA save
    public UserEntity save(UserEntity user) {
        return userRepositoryJpa.save(user);
    }

    //Expensive operation, do not do lightly
    public Iterable<UserEntity> findAllUsers() {
        return userRepositoryJpa.findAll();
    }

    public Optional<UserEntity> findUser(String username) {
        return userRepositoryJpa.findByUsername(username).stream().findFirst();
    }

    public Optional<UserEntity> getUser(String id, String username) {
        return userRepositoryJpa.findById(id, new PartitionKey(username));
    }

    public UserEntity createUser(UserEntity user) {
        return userRepositoryJpa.save(user);
    }

    public List<UserEntity> searchUsername(String username) {
        return userRepositoryJpa.searchUsername(username);
    }

    public Optional<UserGroupEntity> getUserGroup(String username) {
        Optional<UserEntity> foundUser = findUser(username);
        return foundUser.map(UserEntity::getGroup);
    }

    public UserEntity leaveGroup(String username) {
        Optional<UserEntity> foundUser = findUser(username);

        foundUser.orElseThrow(() -> new RuntimeException(USER_NOT_FOUND)).setGroup(null);

        return userRepositoryJpa.save(foundUser.get());
    }

    public void updateAttendeesOfDisband(List<Pair<String,String>> attendees) {
        userRepositoryJpa.findAllById();
        attendees.forEach();
    }
}
