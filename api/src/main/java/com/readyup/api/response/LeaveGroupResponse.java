package com.readyup.api.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.readyup.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeaveGroupResponse {
    @JsonUnwrapped
    private User user;
}
