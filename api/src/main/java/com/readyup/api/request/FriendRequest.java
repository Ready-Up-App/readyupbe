package com.readyup.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FriendRequest {
    private String fromUsername;
    private String toUsername;

}
