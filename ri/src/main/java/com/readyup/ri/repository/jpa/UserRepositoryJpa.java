package com.readyup.ri.repository.jpa;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.readyup.ri.entity.UserEntity;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryJpa extends CosmosRepository<UserEntity, String> {

    //User
    @Query("SELECT * FROM u WHERE u.username = @username")
    List<UserEntity> findByUsername(@Param("username") String username);

    @Query("SELECT * FROM u WHERE u.username LIKE @regex")
    List<UserEntity> searchUsername(@Param("regex") String regex);

    //Notifications

    @Query("SELECT VALUE u.pushToken FROM u WHERE u.username = @username AND IS_DEFINED(u.pushToken)")
    String getNotifTokenForUsername(@Param("username") String username);
}
