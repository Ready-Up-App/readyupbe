package com.readyup.ri.repository;

import com.readyup.ri.entity.PersonEntity;
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

        if (Objects.isNull(from)
            || Objects.isNull(to)) {
            return false;
        }
        from.requestFriend(to);
        personRepositoryJpa.saveAll(List.of(from, to));
        return true;
    }


    public Optional<PersonEntity> findPerson(String username) {
        return personRepositoryJpa.findByUsername(username);
    }
}
