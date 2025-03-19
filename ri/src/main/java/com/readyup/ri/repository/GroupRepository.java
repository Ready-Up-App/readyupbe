package com.readyup.ri.repository;


import com.readyup.ri.aspect.UpdateReadyStatus;
import com.readyup.ri.entity.AttendeeEntity;
import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.UserEntity;
import com.readyup.ri.entity.UserGroupEntity;
import com.readyup.ri.repository.jpa.GroupRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.Collectors;

@Repository
public class GroupRepository {

    private static final String NO_GROUP_FOUND  = "Group not found";

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

    @UpdateReadyStatus
    public GroupEntity leaveGroup(String groupId, String leaverUsername) {
        Optional<GroupEntity> leftGroup = groupRepositoryJpa.findById(groupId);

        if (leftGroup.isEmpty()) {
            throw new RuntimeException(NO_GROUP_FOUND);
        }

        //Check if leaver is owner
        if (leftGroup.get().getOwner().getUsername().equals(leaverUsername)) {
            throw new RuntimeException("Owner cannot leave a group, they must delete it");
        }

        List<AttendeeEntity> removedAttendee = leftGroup.get().getAttendees()
                .stream()
                .filter(attendee -> !leaverUsername.equals(attendee.getUsername()))
                .collect(Collectors.toList());

        leftGroup.get().setAttendees(removedAttendee);

        return groupRepositoryJpa.save(leftGroup.get());
    }

    public Boolean isOwnerOfGroup(String username) {
//        GroupEntity test = groupRepositoryJpa.getOwnedGroup(username);
//        return test != null;
        return null;
    }
}
