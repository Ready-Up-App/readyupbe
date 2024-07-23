package com.readyup.api.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
