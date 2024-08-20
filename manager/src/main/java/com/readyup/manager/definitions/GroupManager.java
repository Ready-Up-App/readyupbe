package com.readyup.manager.definitions;

import com.readyup.domain.Group;
import com.readyup.domain.Person;

import java.util.List;

public interface GroupManager {

    boolean create(String requesterUsername, Group group);
    boolean delete(String username);
    boolean update(Group group);
    Group getGroupFor(String username);
    List<Group> getAllGroups();

    void addMember(String username, String id);

    List<Group> getJoinableGroups(String username);
    Boolean leaveGroup(String username);
}
