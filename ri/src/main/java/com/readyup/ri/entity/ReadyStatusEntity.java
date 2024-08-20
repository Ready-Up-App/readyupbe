package com.readyup.ri.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("ReadyStatus")
@Data
public class ReadyStatusEntity {

    @Id
    @GeneratedValue
    private String id;

    @Property("status")
    private Boolean status;
}
