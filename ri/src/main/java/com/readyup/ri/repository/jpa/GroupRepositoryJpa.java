package com.readyup.ri.repository.jpa;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.UserEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GroupRepositoryJpa extends CosmosRepository<GroupEntity, String> {

//    Optional<GroupEntity> findByName(String name);
//
////    @Query("")
//    Optional<GroupEntity> findByAttendee(String username);
//
////    @Query("")
//    Optional<GroupEntity> addPersonToGroup(String username, String groupId);
//
////    @Query("")
//    List<GroupEntity> findAllJoinableGroupsByUsername(String username);
//
////    @Query("")
//    GroupEntity createGroup(String ownerUsername, Map<String, Object> groupProps);
//
////    @Query("")
//    GroupEntity delete(String username);
//
////    @Query("")
//    GroupEntity leaveGroup(String username);
//
////    @Query("")
//    GroupEntity getOwnedGroup(String username);
//
////    @Query("")
//    GroupEntity setReady(String groupId, Boolean readyStatus);

}
