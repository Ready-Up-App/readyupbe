package com.readyup.manager;

import com.readyup.domain.Group;
import com.readyup.domain.User;
import com.readyup.manager.definitions.GroupManager;
import com.readyup.manager.definitions.UserManager;
import com.readyup.manager.mapper.GroupMapper;
import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.UserEntity;
import com.readyup.ri.entity.UserGroupEntity;
import com.readyup.ri.repository.GroupRepository;
import com.readyup.ri.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupManagerImpl implements GroupManager {

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

        GroupEntity newGroup = groupRepository.create(GroupMapper.INSTANCE.map(group));
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
                .orElseThrow(() -> new RuntimeException("Group not found!"));
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
    public Boolean leaveGroup(String username) {
        if(groupRepository.isOwnerOfGroup(username)) {
            //cant leave a group, must delete it
            throw new RuntimeException("Owner cannot leave a group, they must delete it");
        }
        return groupRepository.leaveGroup(username);
    }

    @Override
    public boolean update(Group group) {
        return groupRepository.update(GroupMapper.INSTANCE.map(group)) != null;
    }

}
