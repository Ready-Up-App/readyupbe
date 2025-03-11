package com.readyup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private String id;

    private String firstname;
    private String email;
    private String username;

    @JsonIgnore
    private String password;


    @JsonIgnore
    private List<Friend> friendsList;

    private Boolean readyStatus;
//    private Group group;
}
