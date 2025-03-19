package com.readyup.manager.definitions;

import com.readyup.domain.Group;
import com.readyup.domain.User;

import java.util.List;

public interface GroupManager {

    void create(User requester, Group group);
    boolean delete(User owner, Group group);
    boolean update(Group group);
    Group getCurrentGroup(String username);
    List<Group> getAllGroups();

    void addMember(String username, String id);

    List<Group> getJoinableGroups(String username);
    User leaveGroup(String groupId, String username);
}
