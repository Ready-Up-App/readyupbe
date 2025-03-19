package com.readyup.ri.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendeeEntity {
    private String id;
    private String username;
    private Boolean readyStatus;
}
