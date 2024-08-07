package com.readyup.manager;

import com.readyup.domain.Friend;
import com.readyup.domain.Person;
import com.readyup.domain.SearchedPerson;
import com.readyup.manager.definitions.PersonManager;
import com.readyup.manager.mapper.FriendMapper;
import com.readyup.manager.mapper.PersonMapper;
import com.readyup.ri.entity.PersonEntity;
import com.readyup.ri.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        CompletableFuture<List<PersonEntity>> friendsFuture = CompletableFuture.supplyAsync(() -> personRepository.getFriends(username));
        CompletableFuture<List<PersonEntity>> pendingFriendsFuture = CompletableFuture.supplyAsync(() -> personRepository.getPendingFriends(username));

        List<PersonEntity> friends = friendsFuture.join();
        List<PersonEntity> pendingFriends = pendingFriendsFuture.join();
        return FriendMapper.map(friends, pendingFriends);
    }

    @Override
    public List<SearchedPerson> searchUsername(String requesterUsername, String username) {
        CompletableFuture<List<PersonEntity>> searchUsernameFuture = CompletableFuture
                .supplyAsync(() -> personRepository.searchUsername(requesterUsername, username));

        CompletableFuture<List<Friend>> allFriendsFuture = CompletableFuture
                .supplyAsync(() -> getPendingAndActiveFriends(requesterUsername));

        List<Person> foundPeople = PersonMapper.INSTANCE.mapAllEntities(searchUsernameFuture.join())
                .stream().toList();

        Map<String, Friend> friendsMap = allFriendsFuture.join().stream().collect(Collectors.toMap(Friend::getUsername,Function.identity()));

        return foundPeople.parallelStream().map(person -> {
            SearchedPerson sp = new SearchedPerson();
            sp.setUsername(person.getUsername());
            sp.setFirstname(person.getFirstname());

            boolean isFriend = friendsMap.containsKey(person.getUsername());
            sp.setAvailable(!isFriend);

            return sp;
        }).toList();
    }

    @Override
    public void respondFriendRequest(String username, String otherUsername, Boolean accept) {
        personRepository.respondFriendRequest(username, otherUsername, accept);
    }

    //gets all friends, and all outgoing+incoming pending requests
    public List<Friend> getPendingAndActiveFriends(String username) {
        return FriendMapper.map(personRepository.getPendingAndActiveFriends(username));
    }

}
