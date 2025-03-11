package com.readyup.manager.definitions;

import com.readyup.domain.Friend;
import com.readyup.domain.SearchedPerson;
import com.readyup.domain.User;

import java.util.List;

public interface UserManager {

    List<User> getAllUsers();
    User getUser(String username);
    User createUser(User person);
//    Boolean friendRequest(String fromUsername, String toUsername);
    Boolean userExists(String username);
    List<Friend> getFriends(String username);
//    List<SearchedPerson> searchUsername(String requesterUsername, String username);
    void respondFriendRequest(String username, String otherUsername, Boolean accept);
    User setReadyStatus(String username, Boolean status);
}
