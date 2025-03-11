package com.readyup.manager;

import com.readyup.domain.Friend;
import com.readyup.domain.User;
import com.readyup.manager.definitions.UserManager;
import com.readyup.manager.definitions.PushNotificationManager;
import com.readyup.manager.mapper.UserMapper;
import com.readyup.ri.entity.UserEntity;
import com.readyup.ri.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Boolean userExists(String username) {
        return userRepository.findUser(username).isPresent();
    }

    @Override
    public List<Friend> getFriends(String username) {
        return List.of();
    }

    @Override
    public void respondFriendRequest(String username, String otherUsername, Boolean accept) {

    }

    @Override
    public User setReadyStatus(String username, Boolean status) {
        return null;
    }
//
//    @Override
//    public List<Friend> getFriends(String username) {
//        CompletableFuture<List<PersonEntity>> friendsFuture = CompletableFuture.supplyAsync(() -> userRepository.getFriends(username));
//        CompletableFuture<List<PersonEntity>> pendingFriendsFuture = CompletableFuture.supplyAsync(() -> userRepository.getPendingFriends(username));
//
//        List<PersonEntity> friends = friendsFuture.join();
//        List<PersonEntity> pendingFriends = pendingFriendsFuture.join();
//        return FriendMapper.map(friends, pendingFriends);
//    }
//
//    @Override
//    public List<SearchedPerson> searchUsername(String requesterUsername, String username) {
//        CompletableFuture<List<PersonEntity>> searchUsernameFuture = CompletableFuture
//                .supplyAsync(() -> userRepository.searchUsername(requesterUsername, username));
//
//        CompletableFuture<List<Friend>> allFriendsFuture = CompletableFuture
//                .supplyAsync(() -> getPendingAndActiveFriends(requesterUsername));
//
//        List<Person> foundPeople = PersonMapper.INSTANCE.mapAllEntities(searchUsernameFuture.join())
//                .stream().toList();
//
//        Map<String, Friend> friendsMap = allFriendsFuture.join().stream().collect(Collectors.toMap(Friend::getUsername,Function.identity()));
//
//        return foundPeople.parallelStream().map(person -> {
//            SearchedPerson sp = new SearchedPerson();
//            sp.setUsername(person.getUsername());
//            sp.setFirstname(person.getFirstname());
//
//            boolean isFriend = friendsMap.containsKey(person.getUsername());
//            sp.setAvailable(!isFriend);
//
//            return sp;
//        }).toList();
//    }
//
//    @Override
//    public void respondFriendRequest(String username, String otherUsername, Boolean accept) {
//        userRepository.respondFriendRequest(username, otherUsername, accept);
//    }
//
//    @Override
//    public Person setReadyStatus(String username, Boolean status) {
//        return PersonMapper.INSTANCE.map(userRepository.setReadyStatus(username, status));
//    }
//
//    //gets all friends, and all outgoing+incoming pending requests
//    public List<Friend> getPendingAndActiveFriends(String username) {
//        return FriendMapper.map(userRepository.getPendingAndActiveFriends(username));
//    }

}
