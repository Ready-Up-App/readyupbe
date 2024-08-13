package com.readyup.api.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
    private String token;
}
