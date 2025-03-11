package com.readyup.api.response;

import com.readyup.domain.Friend;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetFriendsResponse {

    private List<Friend> friends;
}
