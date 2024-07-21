package com.readyup.manager;

import com.readyup.domain.Person;
import com.readyup.manager.definitions.PersonManager;
import com.readyup.manager.mapper.PersonMapper;
import com.readyup.ri.entity.PersonEntity;
import com.readyup.ri.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;

@Service
public class PersonManagerImpl implements PersonManager {


    private final PersonRepository personRepository;

    public PersonManagerImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPeople() {
        return PersonMapper.INSTANCE.mapAllEntities(personRepository.findAllPeople()).stream().toList();
    }

    @Override
    public Person getPerson(String username) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        PersonEntity person = personRepository.findPerson(username);
        stopWatch.stop();
        System.out.println("GetPerson took: " + stopWatch.getTotalTimeSeconds());

        return PersonMapper.INSTANCE.map(person);
    }

    @Override
    public Person createPerson(Person person) {
        return PersonMapper.INSTANCE.map(personRepository.createPerson(PersonMapper.INSTANCE.map(person)));
    }

    @Override
    public void friendRequest(String fromUsername, String toUsername) {
        personRepository.friendRequest(fromUsername, toUsername);
    }

}
