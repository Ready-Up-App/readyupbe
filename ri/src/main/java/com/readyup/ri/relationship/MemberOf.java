package com.readyup.ri.relationship;

import com.readyup.ri.entity.PersonEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Getter
@Setter
@Builder
public class MemberOf {

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private PersonEntity attendee;

    private Boolean owner;

}
