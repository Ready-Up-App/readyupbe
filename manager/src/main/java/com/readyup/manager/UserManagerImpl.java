package com.readyup.manager;

import com.readyup.domain.Friend;
import com.readyup.domain.SearchedPerson;
import com.readyup.domain.User;
import com.readyup.manager.definitions.UserManager;
import com.readyup.manager.definitions.PushNotificationManager;
import com.readyup.manager.mapper.UserMapper;
import com.readyup.ri.entity.UserEntity;
import com.readyup.ri.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagerImpl implements UserManager {

    private static final String FRIEND_REQUEST_PUSH_NOTIF_MSG = "Friend request from %s";

    private final UserRepository userRepository;
    private final PushNotificationManager pushNotificationManager;

    public UserManagerImpl(UserRepository userRepository, PushNotificationManager pushNotificationManager) {
        this.userRepository = userRepository;
        this.pushNotificationManager = pushNotificationManager;
    }

    @Override
    public List<User> getAllUsers() {
        Iterable<UserEntity> usersIterable = userRepository.findAllUsers();
        List<User> users = new ArrayList<>();
        usersIterable.forEach((userEntity -> users.add(UserMapper.INSTANCE.map(userEntity))));
        return users;
    }

    @Override
    public User getUser(String username) {
        UserEntity person = userRepository.findUser(username).orElse(null);

        return UserMapper.INSTANCE.map(person);
    }

    @Override
    public User createUser(User person) {
        return UserMapper.INSTANCE.map(userRepository.createUser(UserMapper.INSTANCE.map(person)));
    }

    @Override
    public Boolean friendRequest(String fromUsername, String toUsername) {

        return null;
    }

    @Override
    public Boolean userExists(String username) {
        return userRepository.findUser(username).isPresent();
    }


    @Override
    public List<Friend> getFriends(String username) {
        Optional<UserEntity> foundUser = userRepository.findUser(username);
        if (foundUser.isEmpty()) {
            //should never be the case
            throw new RuntimeException("CRITICAL ERROR");
        }
        User user = UserMapper.INSTANCE.map(foundUser.get());
        return user.getFriendsList();
    }

    @Override
    public List<SearchedPerson> searchUsername(String requesterUsername, String username) {

        String regex = username.replaceAll("[^a-zA-Z0-9]", "");
        regex = regex.concat("%");
        List<UserEntity> foundUsers = userRepository.searchUsername(regex);
        return foundUsers.stream().map(user -> SearchedPerson.builder()
                    .username(user.getUsername())
                    .firstname(user.getFirstname())
                    .available(null)
                    .build()
            ).toList();
    }

    @Override
    public void respondFriendRequest(String username, String otherUsername, Boolean accept) {
        return;
    }

    @Override
    public User setReadyStatus(String username, Boolean status) {
        return null;
    }

    //gets all friends, and all outgoing+incoming pending requests
    public List<Friend> getPendingAndActiveFriends(String username) {
        return null;
    }

}
