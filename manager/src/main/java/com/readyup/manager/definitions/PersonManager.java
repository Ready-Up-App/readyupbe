package com.readyup.manager.definitions;

import com.readyup.domain.Person;
import com.readyup.manager.mapper.PersonMapper;
import com.readyup.ri.entity.PersonEntity;

import java.util.List;

public interface PersonManager {

    List<Person> getAllPeople();
    Person getPerson(String username);

    Person createPerson(Person person);

    void friendRequest(String fromUsername, String toUsername);

    PersonMapper mapper();
}
