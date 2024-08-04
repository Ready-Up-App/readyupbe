package com.readyup.api.request;

import lombok.Data;

@Data
public class RespondFriendRequest {

    private String otherUsername;
    private Boolean accept;
}
