package com.readyup.api.response;

import lombok.Data;

@Data
public class SignUpResponse extends BaseResponse{

    private String accessToken;
    private String tokenType = "Bearer ";

    public SignUpResponse(String errorRsn) {
        failReason = errorRsn;
    }
    public SignUpResponse() {}

}
