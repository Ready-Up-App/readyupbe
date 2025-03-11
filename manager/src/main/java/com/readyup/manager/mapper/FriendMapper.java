package com.readyup.manager.mapper;

//import com.readyup.domain.Friend;
//import com.readyup.domain.Person;
//import com.readyup.ri.entity.PersonEntity;
//import com.readyup.ri.relationship.FriendWith;
//
//import java.util.ArrayList;
//import java.util.List;

public class FriendMapper {

//    public static List<Friend> map(List<PersonEntity> friends, List<PersonEntity> pendingFriends) {
//        List<Friend> mFriends = friends
//                .stream()
//                .map(p -> {
//                    Friend f = new Friend();
//                    f.setAccepted(true);
//                    f.setFirstName(p.getFirstname());
//                    f.setUsername(p.getUsername());
//                    return f;
//                }).toList();
//
//        List<Friend> pFriends = pendingFriends
//                .stream()
//                .map(p -> {
//                    Friend f = new Friend();
//                    f.setAccepted(false);
//                    f.setFirstName(p.getFirstname());
//                    f.setUsername(p.getUsername());
//                    return f;
//                }).toList();
//
//        List<Friend> allFriends = new ArrayList<>(pFriends.size() + mFriends.size());
//        allFriends.addAll(pFriends);
//        allFriends.addAll(mFriends);
//
//        return allFriends;
//    }
//
//    public static List<Friend> map(List<PersonEntity> allFriends) {
//        return allFriends
//                .stream()
//                .map(p -> {
//                    Friend f = new Friend();
//                    f.setAccepted(true);
//                    f.setFirstName(p.getFirstname());
//                    f.setUsername(p.getUsername());
//                    return f;
//                }).toList();
//    }
//
//    private static List<Friend> mapIncomingFriends(List<PersonEntity> friends) {
//        return friends.parallelStream()
//                .map(personEntity -> {
//                    Friend f = new Friend();
//                    f.setAccepted(personEntity.getFriendsList().get(0).getAccepted());
//                    f.setUsername(personEntity.getUsername());
//                    f.setFirstName(personEntity.getFirstname());
//                    return f;
//                }).toList();
//    }
//    private static List<Friend> mapOutgoingFriends(List<FriendWith> friends) {
//        return friends.parallelStream()
//                .map(friendsWith -> {
//                    Friend f = new Friend();
//
//                    PersonEntity personEntity = friendsWith.getRecipient();
//                    f.setAccepted(friendsWith.getAccepted());
//                    f.setUsername(personEntity.getUsername());
//                    f.setFirstName(personEntity.getFirstname());
//
//                    return f;
//                }).toList();
//    }
}
