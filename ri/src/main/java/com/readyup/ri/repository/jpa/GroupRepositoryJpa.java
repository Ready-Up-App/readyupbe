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
            "MATCH (g)<-[otherRel:MEMBER_OF]-(other:Person) " +
            "RETURN g, collect(otherRel), collect(other), collect(p), collect(rel) ")
    Optional<GroupEntity> findByAttendee(String username);

    @Query("MATCH (p:Person), (g:Group) " +
            "WHERE p.username = $username " +
            "AND elementId(g) = $groupId " +
            "MERGE (p)-[:MEMBER_OF {owner: false}]->(g) " +
            "RETURN g")
    Optional<GroupEntity> addPersonToGroup(String username, String groupId);

    @Query("MATCH (user:Person)-[rel:FRIENDS_WITH]-(:Person)-[:MEMBER_OF]->(g:Group) " +
            "OPTIONAL MATCH (other:Person)-[member:MEMBER_OF]->(g)-[stRel:STATUS]->(rdStat:ReadyStatus) " +
            "WHERE user.username = $username " +
            "AND rel.accepted = TRUE " +
            "RETURN g, collect(stRel), collect(rdStat), collect(other), collect(member) ")
    List<GroupEntity> findAllJoinableGroupsByUsername(String username);


    @Query("MATCH (u:Person) " +
            "WHERE u.username = $ownerUsername " +
            "CREATE (u)-[rel:MEMBER_OF {owner: TRUE}]->(newGroup:Group $groupProps)-[:STATUS]->(st:ReadyStatus {status: FALSE}) " +
            "SET rel.owner = TRUE " +
            "RETURN newGroup ")
    GroupEntity createGroup(String ownerUsername, Map<String, Object> groupProps);

    @Query("MATCH (u:Person)-[rel:MEMBER_OF]->(g:Group)-[:STATUS]->(st:ReadyStatus) " +
            "WHERE u.username = $username " +
            "AND rel.owner = TRUE " +
            "DETACH DELETE g, st")
    GroupEntity delete(String username);

    @Query("MATCH (u:Person)-[rel:MEMBER_OF]->(g:Group) " +
            "WHERE u.username = $username " +
            "DELETE rel " +
            "RETURN g")
    GroupEntity leaveGroup(String username);

    @Query("MATCH (u:Person)-[rel:MEMBER_OF]->(g:Group) " +
            "WHERE u.username = $username " +
            "AND rel.owner = TRUE " +
            "RETURN g ")
    GroupEntity getOwnedGroup(String username);
}
