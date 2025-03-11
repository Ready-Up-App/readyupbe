package com.readyup.ri.repository.jpa;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.readyup.ri.entity.UserEntity;

import com.readyup.ri.entity.UserEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//
//public interface PersonRepositoryJpa extends Neo4jRepository<PersonEntity, Long> {
@Repository
public interface UserRepositoryJpa extends CosmosRepository<UserEntity, String> {

    @Query("SELECT * FROM u WHERE u.username = @username")
    List<UserEntity> findByUsername(@Param("username") String username);


}
