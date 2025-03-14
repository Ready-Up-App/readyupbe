package com.readyup.ri.entity;

import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserGroupEntity {

    @Id
    @GeneratedValue
    private String id;
    private String name;
}
