package com.readyup.ri.repository.jpa;

import com.readyup.ri.entity.GroupEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface GroupRepositoryJpa extends Neo4jRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByName(String name);

    @Query("MATCH (g:Group)<-[:MEMBER_OF]-(p:Person) " +
            "WHERE p.username = $username " +
            "RETURN g")
    Optional<GroupEntity> findByAttendee(String username);
}
