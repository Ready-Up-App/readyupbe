package com.readyup.ri.repository;


import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.UserEntity;
import com.readyup.ri.entity.UserGroupEntity;
import com.readyup.ri.repository.jpa.GroupRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;

@Repository
public class GroupRepository {

    private final GroupRepositoryJpa groupRepositoryJpa;

    public GroupRepository(GroupRepositoryJpa groupRepositoryJpa) {
        this.groupRepositoryJpa = groupRepositoryJpa;
    }


    public GroupEntity create(GroupEntity newGroup) {
        return groupRepositoryJpa.save(newGroup);
    }

    public GroupEntity update(GroupEntity groupEntity) {
//        Optional<GroupEntity> foundGroup = groupRepositoryJpa.findByName(groupEntity.getName());

//        if (foundGroup.isPresent()) {
////            groupEntity.setId(foundGroup.get().getId());
//            //group exists and should update
//            return groupRepositoryJpa.save(groupEntity);
//        }
        return null;
    }

    public Optional<GroupEntity> getGroupFor(String username) {
//        return groupRepositoryJpa.findByAttendee(username);
        return null;
    }

    public Boolean delete(GroupEntity group) {
        groupRepositoryJpa.delete(group);
        return null;
    }

    public List<GroupEntity> getAllGroups() {
//        return groupRepositoryJpa.findAll();
        return null;
    }

    public Optional<GroupEntity> getGroup(String id) {
        return groupRepositoryJpa.findById(id);
    }

    public Optional<GroupEntity> addPersonToGroup(String username, String groupId) {
//        return groupRepositoryJpa.addPersonToGroup(username, groupId);
        return null;
    }

    public List<GroupEntity> getJoinableGroups(String username) {
//        List<GroupEntity> ge = groupRepositoryJpa.findAllJoinableGroupsByUsername(username);
        return null;
    }

    public Boolean leaveGroup(String username) {
//        GroupEntity leftGroup = groupRepositoryJpa.leaveGroup(username);
//        if (leftGroup != null) {
//            groupRepositoryJpa.setReady(leftGroup.getId(), false);
//        }
//        return leftGroup != null;
        return null;
    }

    public Boolean isOwnerOfGroup(String username) {
//        GroupEntity test = groupRepositoryJpa.getOwnedGroup(username);
//        return test != null;
        return null;
    }
}
