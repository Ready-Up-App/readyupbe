package com.readyup.domain;

import lombok.*;

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
//    private Group group;
}
