package com.readyup.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePersonRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
}
