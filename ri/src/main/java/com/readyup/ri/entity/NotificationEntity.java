package com.readyup.ri.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashMap;
import java.util.Map;

@Node("NotificationToken")
@Data
public class NotificationEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String pushToken;

    @Relationship(type = "TOKEN_OF", direction = Relationship.Direction.OUTGOING)
    private PersonEntity owner;

    public Map<String, Object> getProps() {
        Map<String, Object> props = new HashMap<>();
        props.put("pushToken", pushToken);

        return props;
    }
}
