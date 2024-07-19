package com.readyup.manager;

import com.readyup.domain.Person;
import com.readyup.manager.definitions.PersonManager;
import com.readyup.manager.mapper.PersonMapper;
import com.readyup.ri.entity.PersonEntity;
import com.readyup.ri.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonManagerImpl implements PersonManager {


    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonManagerImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public List<Person> getAllPeople() {
        return personRepository.findAllPeople().stream().map(personMapper::entityToDomain).toList();
    }

    @Override
    public Person getPerson(String username) {
        return personMapper.entityToDomain(personRepository.findPerson(username));
    }

    @Override
    public Person createPerson(Person person) {
        return personMapper.entityToDomain(personRepository.createPerson(personMapper.domainToEntity(person)));
    }

    @Override
    public void friendRequest(String fromUsername, String toUsername) {
        personRepository.friendRequest(fromUsername, toUsername);
    }

    @Override
    public PersonMapper mapper() {
        return personMapper;
    }


}
