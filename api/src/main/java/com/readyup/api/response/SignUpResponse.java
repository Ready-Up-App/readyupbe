package com.readyup.api.response;

import lombok.Data;

@Data
public class SignUpResponse extends BaseResponse{

    private String accessToken;

    public SignUpResponse(String errorRsn) {
//        failReason = errorRsn;
    }
    public SignUpResponse() {}

}
