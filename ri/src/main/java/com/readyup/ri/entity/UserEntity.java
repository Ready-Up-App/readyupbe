package com.readyup.ri.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Container(containerName = "User")
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    private String id;

    private String username;
    private String password;
    private String firstname;
    private String email;

    private List<FriendEntity> friendsList;

    private String pushToken;
//    @Relationship(type = "STATUS", direction = Relationship.Direction.OUTGOING)
//    private ReadyStatusEntity readyStatus;
//
//    public PersonEntity() {}
//
//    public void requestFriend(PersonEntity person) {
//        if (friendsList == null) {
//            friendsList = new ArrayList<>();
//        }
//        friendsList.add(FriendWith.builder().recipient(person).accepted(false).build());
//    }

}
