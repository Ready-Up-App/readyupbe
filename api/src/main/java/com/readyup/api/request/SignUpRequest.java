package com.readyup.api.request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
}
