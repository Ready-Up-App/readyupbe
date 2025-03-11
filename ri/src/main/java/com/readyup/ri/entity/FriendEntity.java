package com.readyup.ri.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class FriendEntity {
    @Id
    private String id;
    private String username;
}
