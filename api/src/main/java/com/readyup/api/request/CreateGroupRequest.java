package com.readyup.api.request;

import com.readyup.domain.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateGroupRequest {
    private Group group;
    private String requesterUsername;
}