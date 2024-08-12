package com.readyup.ri.repository.jpa;

import com.readyup.ri.entity.NotificationEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Map;

public interface NotificationRepositoryJpa extends Neo4jRepository<NotificationEntity, Long> {

    @Query("MATCH (u:Person) " +
            "WHERE u.username = $username " +
            "MERGE (u)<-[:TOKEN_OF]-(n:NotificationToken) " +
            "SET n = $notificationProps")
    void createNotificationTokenForUsername(String username, Map<String, Object> notificationProps);

    @Query("MATCH (u:Person)<-[:TOKEN_OF]-(t:NotificationToken) " +
            "WHERE u.username = $username " +
            "RETURN t")
    NotificationEntity getNotificationTokenForUsername(String username);


    @Query("MATCH (u:Person)<-[:TOKEN_OF]-(n:NotificationToken) " +
            "WHERE u.username = $username " +
            "DETACH DELETE n ")
    void removeNotificationTokenForUsername(String username);
}
