package com.readyup.ri.repository.jpa;

import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GroupRepositoryJpa extends Neo4jRepository<GroupEntity, String> {

    Optional<GroupEntity> findByName(String name);

    @Query("MATCH (p:Person)-[rel:MEMBER_OF]->(g:Group) " +
            "WHERE p.username = $username " +
            "WITH g AS g, collect(rel) AS rel, collect(p) AS p " +
            "MATCH (g)<-[otherRel:MEMBER_OF]-(other:Person)-[s:STATUS]->(rs:ReadyStatus) " +
            "RETURN g, collect(otherRel), collect(other), collect(s), collect(rs), p, rel ")
    Optional<GroupEntity> findByAttendee(String username);

    @Query("MATCH (p:Person), (g:Group) " +
            "WHERE p.username = $username " +
            "AND elementId(g) = $groupId " +
            "MERGE (p)-[:MEMBER_OF {owner: false}]->(g) " +
            "MERGE (p)-[:STATUS]->(:ReadyStatus {status: false}) " +
            "RETURN g ")
    Optional<GroupEntity> addPersonToGroup(String username, String groupId);

    @Query("MATCH (user:Person)-[rel:FRIENDS_WITH]-(:Person)-[:MEMBER_OF]->(g:Group) " +
            "OPTIONAL MATCH (other:Person)-[member:MEMBER_OF]->(g) " +
            "WHERE user.username = $username " +
            "AND rel.accepted = TRUE " +
            "RETURN g, collect(other), collect(member) ")
    List<GroupEntity> findAllJoinableGroupsByUsername(String username);


    @Query("MATCH (u:Person) " +
            "WHERE u.username = $ownerUsername " +
            "CREATE (u)-[rel:MEMBER_OF {owner: TRUE}]->(newGroup:Group $groupProps)-[:STATUS]->(st:ReadyStatus {status: FALSE}) " +
            "CREATE (:ReadyStatus {status: false})<-[:STATUS]-(u) " +
            "SET rel.owner = TRUE " +
            "RETURN newGroup ")
    GroupEntity createGroup(String ownerUsername, Map<String, Object> groupProps);

    @Query("MATCH (u:Person)-[rel:MEMBER_OF]->(g:Group)" +
            "OPTIONAL MATCH (g)-[:STATUS]->(st:ReadyStatus) " +
            "OPTIONAL MATCH (ost:ReadyStatus)<-[:STATUS]-(other:Person)-[:MEMBER_OF]->(g)" +
            "WHERE u.username = $username " +
            "AND rel.owner = TRUE " +
            "DETACH DELETE g, st, ost")
    GroupEntity delete(String username);

    @Query("MATCH (u:Person)-[rel:MEMBER_OF]->(g:Group) " +
            "WHERE u.username = $username " +
            "OPTIONAL MATCH (u)-[:STATUS]-(rs:ReadyStatus) " +
            "DELETE rel " +
            "DETACH DELETE rs " +
            "RETURN g")
    GroupEntity leaveGroup(String username);

    @Query("MATCH (u:Person)-[rel:MEMBER_OF]->(g:Group) " +
            "WHERE u.username = $username " +
            "AND rel.owner = TRUE " +
            "RETURN g ")
    GroupEntity getOwnedGroup(String username);

    @Query("MATCH (g:Group)-[s:STATUS]->(rs:ReadyStatus) " +
            "WHERE elementId(g) = $groupId " +
            "SET rs.status = $readyStatus " +
            "RETURN g ")
    GroupEntity setReady(String groupId, Boolean readyStatus);

//    GroupEntity playerReadyUp(String username);
}
