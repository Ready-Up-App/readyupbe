package com.readyup.manager;

import com.readyup.domain.Group;
import com.readyup.domain.User;
import com.readyup.manager.definitions.GroupManager;
import com.readyup.manager.mapper.GroupMapper;
import com.readyup.manager.mapper.UserMapper;
import com.readyup.ri.entity.AttendeeEntity;
import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.UserEntity;
import com.readyup.ri.entity.UserGroupEntity;
import com.readyup.ri.repository.GroupRepository;
import com.readyup.ri.repository.UserRepository;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupManagerImpl implements GroupManager {

    private static final String NO_GROUP_FOUND  = "Group not found";

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupManagerImpl(GroupRepository groupRepository, GroupMapper groupMapper, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(User owner, Group group) {
        Optional<UserEntity> foundUser = userRepository.getUser(owner.getId(), owner.getUsername());

        if (foundUser.isEmpty()) {
            throw new RuntimeException("Critical error, no user found");
        }
        if (foundUser.get().getGroup() != null) {
            //what happens if the users group is no longer valid?
            throw new RuntimeException("User is already in a group");
        }

        GroupEntity newGroup =  GroupMapper.INSTANCE.map(group);
        newGroup.setOwner(AttendeeEntity.builder()
                .id(owner.getId())
                .username(owner.getUsername())
                .readyStatus(false)
                .build());

        newGroup = groupRepository.create(newGroup);

        foundUser.get().setGroup(GroupMapper.INSTANCE.mapToUserGroup(newGroup));
        userRepository.save(foundUser.get());
    }

    @Override
    public boolean delete(User owner, Group group) {
        Optional<UserEntity> foundUser = userRepository.getUser(owner.getId(), owner.getUsername());

        if (foundUser.isEmpty()) {
            throw new RuntimeException("Critical error, no user found");
        }
        if (foundUser.get().getGroup() == null) {
            throw new RuntimeException("User Is not in a group");
        }
        Optional<GroupEntity> groupToDelete = groupRepository.getGroup(group.getId());
        if (groupToDelete.isEmpty()) {
            throw new RuntimeException(NO_GROUP_FOUND);
        }


        List<Pair<String, String>> idUsers = new ArrayList<>();
        groupToDelete.get()
                .getAttendees()
                .forEach(attendee -> idUsers.add(new Pair<>(attendee.getId(), attendee.getUsername())));

        userRepository.;
        groupRepository.delete(GroupMapper.INSTANCE.map(group));
        foundUser.get().setGroup(null);
        userRepository.save(foundUser.get());
        return false;
    }

    @Override
    public Group getCurrentGroup(String username) {
        //Validation based on user?
        Optional<UserGroupEntity> currGroup = userRepository.getUserGroup(username);
        if (currGroup.isEmpty()) {
            return null;
        }
        GroupEntity foundGroup = groupRepository.getGroup(currGroup.get().getId())
                .orElseThrow(() -> new RuntimeException(NO_GROUP_FOUND));
        return GroupMapper.INSTANCE.map(foundGroup);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups().stream().map(GroupMapper.INSTANCE::map).toList();
    }

    @Override
    public void addMember(String username, String groupId) {

        groupRepository.addPersonToGroup(username, groupId);
    }

    @Override
    public List<Group> getJoinableGroups(String username) {
        return GroupMapper.INSTANCE.mapAllEntities(groupRepository.getJoinableGroups(username))
                .stream().toList();
    }

    @Override
    public User leaveGroup(String groupId, String username) {

        groupRepository.leaveGroup(groupId, username);
        return UserMapper.INSTANCE.map(userRepository.leaveGroup(username));
    }

    @Override
    public boolean update(Group group) {
        return groupRepository.update(GroupMapper.INSTANCE.map(group)) != null;
    }

}
