package com.readyup.api.response;

import lombok.Data;

@Data
public class SignInResponse {

    private String accessToken;
    private String tokenType = "Bearer ";

    public SignInResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
