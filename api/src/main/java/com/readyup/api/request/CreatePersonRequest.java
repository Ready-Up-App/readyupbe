package com.readyup.api.request;

import lombok.Data;

@Data
public class CreatePersonRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
}
