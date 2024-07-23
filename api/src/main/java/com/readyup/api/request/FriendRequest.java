package com.readyup.api.request;

import lombok.Data;

@Data
public class FriendRequest {
    private String fromUsername;
    private String toUsername;

}
