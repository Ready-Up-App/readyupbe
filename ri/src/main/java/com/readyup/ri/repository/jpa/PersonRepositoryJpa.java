package com.readyup.ri.repository.jpa;

import com.readyup.ri.entity.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface PersonRepositoryJpa extends Neo4jRepository<PersonEntity, Long> {
    @Query("MATCH (p:Person) " +
            "WHERE p.username = $username " +
            "RETURN p")
    PersonEntity findByUsername(String username);

    @Query("MATCH (p:Person) " +
            "WHERE p.username in $usernames " +
            "RETURN p")
    List<PersonEntity> findByUsernames(List<String> usernames);

    @Query("MATCH (requester:Person)-[rel:FRIENDS_WITH]-(friend:Person) " +
            "WHERE requester.username = $username AND rel.accepted")
    List<PersonEntity> findFriends(String username);

}
