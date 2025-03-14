package com.readyup.domain;

import lombok.Data;

@Data
public class Attendee {
    private String id;
    private String username;
    private Boolean readyStatus;
}
