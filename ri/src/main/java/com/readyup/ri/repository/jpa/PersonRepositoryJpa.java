package com.readyup.ri.repository.jpa;

import com.readyup.ri.entity.PersonEntity;
import org.neo4j.driver.internal.value.MapValue;
import org.springframework.data.neo4j.core.mapping.MapValueWrapper;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Map;

public interface PersonRepositoryJpa extends Neo4jRepository<PersonEntity, Long> {
    @Query("MATCH (p:Person) " +
            "WHERE p.username = $username " +
            "RETURN p")
    PersonEntity findByKey(String username);

    @Query("MATCH (p:Person) " +
            "WHERE p.username in $usernames " +
            "RETURN p")
//    "RETURN {key: 'Value', listKey: [{inner: 'Map1'}, {inner: 'Map2'}]} AS map")
    List<PersonEntity> findByKeys(List<String> usernames);

//    @Override
//    @Query("MATCH (:Person)-[:FRIENDS_WITH]-(:Person) " +
//            "WHERE ")
//    List<PersonEntity> findAll();
}
