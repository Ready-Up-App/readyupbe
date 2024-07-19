package com.readyup.manager.definitions;

import com.readyup.domain.Group;
import com.readyup.domain.Person;

import java.util.List;

public interface GroupManager {

    boolean create(Group group, String requesterUsername);
    Group getGroupFor(Person person);
    boolean update(Group group);
    boolean delete(Group group);
    List<Group> getAllGroups();

    void addMember(String username, String groupUid);
}
