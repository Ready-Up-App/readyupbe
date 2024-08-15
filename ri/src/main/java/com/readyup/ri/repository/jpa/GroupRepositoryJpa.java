package com.readyup.ri.repository.jpa;

import com.readyup.ri.entity.GroupEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GroupRepositoryJpa extends Neo4jRepository<GroupEntity, String> {

    Optional<GroupEntity> findByName(String name);

    @Query("MATCH (g:Group)<-[:MEMBER_OF]-(p:Person) " +
            "WHERE p.username = $username " +
            "RETURN g")
    Optional<GroupEntity> findByAttendee(String username);

    @Query("MATCH (p:Person), (g:Group) " +
            "WHERE p.username = $username " +
            "AND elementId(g) = $groupId " +
            "CREATE (p)-[:MEMBER_OF {owner: false}]->(g) " +
            "RETURN g")
    Optional<GroupEntity> addPersonToGroup(String username, String groupId);

    @Query("MATCH (user:Person)-[rel:FRIENDS_WITH]-(other:Person)-[:MEMBER_OF]->(g:Group) " +
            "WHERE user.username = $username " +
            "AND rel.accepted = TRUE " +
            "RETURN g ")
    List<GroupEntity> findAllJoinableGroupsByUsername(String username);


    @Query("MATCH (u:Person) " +
            "WHERE u.username = $ownerUsername " +
            "CREATE (u)-[rel:MEMBER_OF {owner: TRUE}]->(newGroup:Group $groupProps) " +
            "SET rel.owner = TRUE " +
            "RETURN newGroup ")
    GroupEntity createGroup(String ownerUsername, Map<String, Object> groupProps);

    @Query("MATCH (u:Person)-[rel:MEMBER_OF]->(g:Group) " +
            "WHERE u.username = $username " +
            "AND rel.owner = TRUE " +
            "DETACH DELETE g ")
    GroupEntity delete(String username);
}
