//package com.readyup.ri.repository;
////
////
////import com.readyup.ri.entity.GroupEntity;
////import com.readyup.ri.entity.PersonEntity;
////import com.readyup.ri.repository.jpa.GroupRepositoryJpa;
//import org.springframework.stereotype.Repository;
////
////import java.util.List;
////import java.util.Map;
////import java.util.Optional;
////
//@Repository
//public class GroupRepository {
////
////    private final GroupRepositoryJpa groupRepositoryJpa;
////
////    public GroupRepository(GroupRepositoryJpa groupRepositoryJpa) {
////        this.groupRepositoryJpa = groupRepositoryJpa;
////    }
////
////
////    //If user is in a group, do not create a new one.
////    public GroupEntity create(String ownerUsername, Map<String, Object> groupProps) {
////        try {
////            return getGroupFor(ownerUsername).isPresent() ? null : groupRepositoryJpa.createGroup(ownerUsername, groupProps);
////
////        } catch (Exception e) {
////            System.out.print(e.getMessage());
////            return null;
////        }
////    }
////
////    public GroupEntity update(GroupEntity groupEntity) {
////        Optional<GroupEntity> foundGroup = groupRepositoryJpa.findByName(groupEntity.getName());
////
////        if (foundGroup.isPresent()) {
//////            groupEntity.setId(foundGroup.get().getId());
////            //group exists and should update
////            return groupRepositoryJpa.save(groupEntity);
////        }
////        return null;
////    }
////
////    public Optional<GroupEntity> getGroupFor(String username) {
////        return groupRepositoryJpa.findByAttendee(username);
////    }
////
////    public Boolean delete(String username) {
////        return groupRepositoryJpa.delete(username) == null;
////    }
////
////    public List<GroupEntity> getAllGroups() {
////        return groupRepositoryJpa.findAll();
////    }
////
////    public Optional<GroupEntity> getGroup(String id) {
////        return groupRepositoryJpa.findById(id);
////    }
////
////    public Optional<GroupEntity> addPersonToGroup(String username, String groupId) {
////        return groupRepositoryJpa.addPersonToGroup(username, groupId);
////    }
////
////    public List<GroupEntity> getJoinableGroups(String username) {
////        List<GroupEntity> ge = groupRepositoryJpa.findAllJoinableGroupsByUsername(username);
////        return ge;
////    }
////
////    public Boolean leaveGroup(String username) {
////        GroupEntity leftGroup = groupRepositoryJpa.leaveGroup(username);
////        if (leftGroup != null) {
////            groupRepositoryJpa.setReady(leftGroup.getId(), false);
////        }
////        return leftGroup != null;
////    }
////
////    public Boolean isOwnerOfGroup(String username) {
////        GroupEntity test = groupRepositoryJpa.getOwnedGroup(username);
////        return test != null;
//    }
////}
