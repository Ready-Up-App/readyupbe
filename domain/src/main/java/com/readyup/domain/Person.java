package com.readyup.domain;

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
//    private List<Person> friendsList;
}
