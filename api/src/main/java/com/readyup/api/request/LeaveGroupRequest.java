package com.readyup.api.request;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.readyup.domain.Group;
import lombok.Data;

@Data
public class LeaveGroupRequest {
    @JsonUnwrapped
    private Group group;
}
