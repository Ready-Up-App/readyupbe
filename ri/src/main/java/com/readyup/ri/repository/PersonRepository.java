package com.readyup.ri.repository;

import com.readyup.ri.entity.PersonEntity;
import com.readyup.ri.relationship.FriendWith;
import com.readyup.ri.repository.jpa.PersonRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {

    private final PersonRepositoryJpa personRepositoryJpa;

    public PersonRepository(PersonRepositoryJpa personRepositoryJpa) {
        this.personRepositoryJpa = personRepositoryJpa;
    }

    //Expensive operation, do not do lightly
    public List<PersonEntity> findAllPeople() {
        return personRepositoryJpa.findAll();
    }

    public PersonEntity createPerson(PersonEntity person) {
        //throws exception due to constraint on database for unique username
        return personRepositoryJpa.save(person);
    }

    public Boolean friendRequest(String fromUsername, String toUsername) {

        Map<String, PersonEntity> peopleMap = personRepositoryJpa.findByUsernames(List.of(fromUsername, toUsername))
                .stream().collect(Collectors.toMap(PersonEntity::getUsername, Function.identity()));
        PersonEntity from = peopleMap.get(fromUsername);
        PersonEntity to = peopleMap.get(toUsername);

        if (from == null || to == null) {
            return false;
        }

        //check both directions for whether they are friends
        if (areFriends(to, from)) {
            return false;
        }

        from.requestFriend(to);
        personRepositoryJpa.saveAll(List.of(from, to));
        return true;
    }


    public Optional<PersonEntity> findPerson(String username) {
        return personRepositoryJpa.findByUsername(username);
    }

    public boolean areFriends(PersonEntity first, PersonEntity second) {
        Optional<FriendWith> friendWith = first.getFriendsList().parallelStream()
                .filter((friend) -> friend.getRecipient().getUsername().equals(second.getUsername()))
                .findFirst();
        if (friendWith.isPresent()) {
            return true;
        }

        friendWith = second.getFriendsList().parallelStream()
                .filter((friend) -> friend.getRecipient().getUsername().equals(first.getUsername()))
                .findFirst();
        return friendWith.isPresent();
    }

    public List<PersonEntity> getFriends(String username) {
        return personRepositoryJpa.findFriends(username);
    }

    public List<PersonEntity> getPendingFriends(String username) {
        return personRepositoryJpa.findPendingFriends(username);
    }

    public List<PersonEntity> searchUsername(String requesterUsername, String username) {
        //Remove all non-alphanumeric characters
        username = username.replaceAll("\\W", "");

        if (username.isEmpty()) {
            throw new RuntimeException("Empty string!");
        }
        return personRepositoryJpa.searchUsername(requesterUsername, String.format("(?i)%s.*", username));
    }

    public void respondFriendRequest(String username, String otherUsername, Boolean accept) {

        if (accept) {
            personRepositoryJpa.acceptFriendRequest(username, otherUsername);
        } else {
            personRepositoryJpa.rejectFriendRequest(username, otherUsername);
        }
    }

    public List<PersonEntity> getPendingAndActiveFriends(String username) {
        return personRepositoryJpa.getPendingAndActiveFriends(username);
    }

    public PersonEntity setReadyStatus(String username, Boolean status) {
        return personRepositoryJpa.setReadyStatus(username, status);
    }
}
