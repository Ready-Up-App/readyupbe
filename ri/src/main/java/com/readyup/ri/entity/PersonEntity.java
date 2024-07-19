package com.readyup.ri.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Node("Person")
@Data
public class PersonEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property("firstname")
    private String firstname;

    @Property("lastname")
    private String lastname;

//    @Id
    @Property("username")
    private String username;

    @Property("email")
    private String email;

    @Relationship(type = "FRIENDS_WITH", direction = Relationship.Direction.INCOMING)
    private List<PersonEntity> friendsList;

    public PersonEntity() {}

    public void addFriend(PersonEntity person) {
        if (friendsList == null) {
            friendsList = new ArrayList<>();
        }
        friendsList.add(person);
    }
}
