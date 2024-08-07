package com.readyup.manager.definitions;

import com.readyup.domain.Group;
import com.readyup.domain.Person;

import java.util.List;

public interface GroupManager {

    boolean create(String requesterUsername, Group group);
    Group getGroupFor(String username);
    boolean update(Group group);
    boolean delete(Group group);
    List<Group> getAllGroups();

    void addMember(String username, String groupUid);

    List<Group> getJoinableGroups(String username);
}
