package com.readyup.api.request;

import com.readyup.domain.Group;
import com.readyup.domain.User;
import lombok.Data;

@Data
public class CreateGroupRequest {
    private User user;
    private Group group;
}