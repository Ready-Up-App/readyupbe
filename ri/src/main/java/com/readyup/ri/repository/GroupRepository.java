package com.readyup.ri.repository;


import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.PersonEntity;
import com.readyup.ri.repository.jpa.GroupRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GroupRepository {

    private final GroupRepositoryJpa groupRepositoryJpa;

    public GroupRepository(GroupRepositoryJpa groupRepositoryJpa) {
        this.groupRepositoryJpa = groupRepositoryJpa;
    }


    public GroupEntity create(GroupEntity groupEntity) {
        Optional<GroupEntity> foundGroup = groupRepositoryJpa.findByName(groupEntity.getName());
        if (foundGroup.isPresent()) {
            //group exists and should not be created
            return null;
        }
        return groupRepositoryJpa.save(groupEntity);
    }

    public GroupEntity update(GroupEntity groupEntity) {
        Optional<GroupEntity> foundGroup = groupRepositoryJpa.findByName(groupEntity.getName());

        if (foundGroup.isPresent()) {
            groupEntity.setId(foundGroup.get().getId());
            //group exists and should update
            return groupRepositoryJpa.save(groupEntity);
        }
        return null;
    }

    public Optional<GroupEntity> getGroupFor(String username) {
        return groupRepositoryJpa.findByAttendee(username);
    }

    public Boolean delete(GroupEntity groupEntity) {
        Optional<GroupEntity> foundGroup = groupRepositoryJpa.findByName(groupEntity.getName());
        if (foundGroup.isPresent()) {
            groupRepositoryJpa.deleteById(foundGroup.get().getId());
            return true;
        }
        return false;
    }

    public List<GroupEntity> getAllGroups() {
        return groupRepositoryJpa.findAll();
    }

    public Optional<GroupEntity> getGroup(String groupUid) {
        return groupRepositoryJpa.findByName(groupUid);
    }

    public Optional<GroupEntity> addPersonToGroup(String groupUid, String username) {
        return groupRepositoryJpa.addPersonToGroup(groupUid, username);
    }

    public List<GroupEntity> getJoinableGroups(String username) {
        List<GroupEntity> ge = groupRepositoryJpa.findAllJoinableGroupsByUsername(username);
        return ge;
    }
}
