package com.readyup.ri.entity;

import com.readyup.ri.relationship.FriendWith;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Node("Person")
@Data
public class PersonEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property("username")
    private String username;
    @Property("password")
    private String password;
    @Property("firstname")
    private String firstname;
    @Property("email")
    private String email;
    @Property("createDtm")
    private LocalDateTime createDtm;

    @Relationship(type = "FRIENDS_WITH", direction = Relationship.Direction.OUTGOING)
    private List<FriendWith> friendsList;

    public PersonEntity() {}

    public void requestFriend(PersonEntity person) {
        if (friendsList == null) {
            friendsList = new ArrayList<>();
        }
        friendsList.add(FriendWith.builder().recipient(person).accepted(false).build());
    }

}
