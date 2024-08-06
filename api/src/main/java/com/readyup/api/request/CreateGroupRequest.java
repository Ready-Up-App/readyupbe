package com.readyup.api.request;

import com.readyup.domain.Group;
import lombok.Data;

@Data
public class CreateGroupRequest {
    private Group group;
}