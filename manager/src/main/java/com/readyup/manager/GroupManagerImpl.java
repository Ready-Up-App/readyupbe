package com.readyup.manager;
//
//import com.readyup.domain.Group;
import com.readyup.manager.definitions.GroupManager;
//import com.readyup.manager.definitions.UserManager;
//import com.readyup.manager.mapper.GroupMapper;
//import com.readyup.ri.entity.GroupEntity;
//import com.readyup.ri.entity.PersonEntity;
//import com.readyup.ri.repository.GroupRepository;
//import com.readyup.ri.repository.PersonRepository;
import org.springframework.stereotype.Service;
//
//import java.util.*;
//
@Service
public class GroupManagerImpl implements GroupManager {
//
//    private final GroupRepository groupRepository;
//    private final UserManager userManager;
//
//    public GroupManagerImpl(GroupRepository groupRepository, GroupMapper groupMapper, UserManager userManager) {
//        this.groupRepository = groupRepository;
//        this.userManager = userManager;
//    }
//
//    @Override
//    public boolean create(String ownerUsername, Group group) {
//        return groupRepository.create(ownerUsername, group.getProps()) != null;
//    }
//
//    @Override
//    public boolean delete(String username) {
//        return groupRepository.delete(username);
//    }
//
//    @Override
//    public Group getGroupFor(String username) {
//        Optional<GroupEntity> foundGroup = groupRepository.getGroupFor(username);
//        return foundGroup.map(GroupMapper.INSTANCE::map).orElse(null);
//    }
//
//    @Override
//    public List<Group> getAllGroups() {
//        return groupRepository.getAllGroups().stream().map(GroupMapper.INSTANCE::map).toList();
//    }
//
//    @Override
//    public void addMember(String username, String groupId) {
//
//        groupRepository.addPersonToGroup(username, groupId);
//    }
//
//    @Override
//    public List<Group> getJoinableGroups(String username) {
//        return GroupMapper.INSTANCE.mapAllEntities(groupRepository.getJoinableGroups(username))
//                .stream().toList();
//    }
//
//    @Override
//    public Boolean leaveGroup(String username) {
//        if(groupRepository.isOwnerOfGroup(username)) {
//            //cant leave a group, must delete it
//            throw new RuntimeException("Owner cannot leave a group, they must delete it");
//        }
//        return groupRepository.leaveGroup(username);
//    }
//
//    @Override
//    public boolean update(Group group) {
//        return groupRepository.update(GroupMapper.INSTANCE.map(group)) != null;
//    }
//
}
