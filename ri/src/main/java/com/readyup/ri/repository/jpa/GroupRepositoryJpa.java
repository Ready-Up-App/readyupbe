package com.readyup.ri.repository.jpa;

import com.readyup.ri.entity.GroupEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GroupRepositoryJpa extends Neo4jRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByName(String name);

    @Query("MATCH (g:Group)<-[:MEMBER_OF]-(p:Person) " +
            "WHERE p.username = $username " +
            "RETURN g")
    Optional<GroupEntity> findByAttendee(String username);

    @Query("MATCH (p:Person) " +
            "WHERE p.username = $username " +
            "MATCH (g:Group) " +
            "WHERE g.name = $groupUid " +
            "CREATE (g)<-[:MEMBER_OF {owner: true}]-(p) " +
            "RETURN g")
    Optional<GroupEntity> addPersonToGroup(String groupUid, String username);

    @Query("MATCH (user:Person)-[:FRIENDS_WITH]-(other:Person)-[:MEMBER_OF]->(g:Group) " +
            "WHERE user.username = $username " +
            "RETURN g")
    List<GroupEntity> findAllJoinableGroupsByUsername(String username);


    @Query("MATCH (u:Person) " +
            "WHERE u.username = $ownerUsername " +
            "CREATE (u)-[rel:MEMBER_OF {owner: TRUE}]->(newGroup:Group $groupProps) " +
            "SET rel.owner = TRUE " +
            "RETURN newGroup ")
    GroupEntity createGroup(String ownerUsername, Map<String, Object> groupProps);
}
