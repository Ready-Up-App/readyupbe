package com.readyup.ri.repository.jpa;

import com.readyup.ri.entity.PersonEntity;
import com.readyup.ri.relationship.FriendWith;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepositoryJpa extends Neo4jRepository<PersonEntity, Long> {
    @Query("MATCH (p:Person) " +
            "WHERE p.username = $username " +
            "OPTIONAL MATCH (p)-[f:FRIENDS_WITH]->(other:Person)" +
            "RETURN p, collect(f), collect(other)")
    Optional<PersonEntity> findByUsername(String username);

    @Query("MATCH (p:Person)" +
            "WHERE p.username in $usernames " +
            "OPTIONAL MATCH (p)-[f:FRIENDS_WITH]->(other:Person) " +
            "RETURN p, collect(f), collect(other)")
    List<PersonEntity> findByUsernames(List<String> usernames);

//    @Query("MATCH path = (p:Person {username: $username})-[rel:FRIENDS_WITH]-(friend:Person) " +
//            "RETURN nodes(path) as PersonEntity, collect(rel)")
    @Query("MATCH (p:Person)-[rel:FRIENDS_WITH]-(friend:Person) " +
            "WHERE p.username = $username AND rel.accepted = TRUE " +
            "RETURN friend")
    List<PersonEntity> findFriends(String username);

    @Query("MATCH (p:Person)-[rel:FRIENDS_WITH]-(friend:Person) " +
            "WHERE p.username = $username AND rel.accepted = FALSE " +
            "RETURN friend")
    List<PersonEntity> findPendingFriends(String username);

}
