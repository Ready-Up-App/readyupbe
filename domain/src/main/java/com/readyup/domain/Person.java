package com.readyup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person {

    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String createDtm;

    @JsonIgnore
    private List<Person> friendsList;
//    private Group group;
}
