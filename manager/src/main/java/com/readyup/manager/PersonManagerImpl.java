package com.readyup.manager;

import com.readyup.domain.Friend;
import com.readyup.domain.Person;
import com.readyup.manager.definitions.PersonManager;
import com.readyup.manager.mapper.FriendMapper;
import com.readyup.manager.mapper.PersonMapper;
import com.readyup.ri.entity.PersonEntity;
import com.readyup.ri.repository.PersonRepository;
import org.springframework.stereotype.Service;

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
        PersonEntity person = personRepository.findPerson(username).orElse(null);

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

    @Override
    public Boolean personExists(String username) {
        return personRepository.findPerson(username).isPresent();
    }

    @Override
    public List<Friend> getFriends(String username) {
        List<PersonEntity> friends = personRepository.getFriends(username);
        List<PersonEntity> pendingFriends = personRepository.getPendingFriends(username);
        
        return FriendMapper.map(friends, pendingFriends);
    }

    @Override
    public List<Person> searchUsername(String username) {
        return PersonMapper.INSTANCE.mapAllEntities(personRepository.searchUsername(username))
                .stream().toList();
    }

    @Override
    public void respondFriendRequest(String username, String otherUsername, Boolean accept) {
        personRepository.respondFriendRequest(username, otherUsername, accept);
    }


}
