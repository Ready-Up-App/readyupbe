package com.readyup.api.endpoint;

import com.readyup.api.endpointdefinition.PersonEndpointDefinition;
import com.readyup.api.request.CreatePersonRequest;
import com.readyup.api.request.FriendRequest;
import com.readyup.api.response.CreatePersonResponse;
import com.readyup.api.validator.Validator;
import com.readyup.domain.Person;
import com.readyup.manager.definitions.PersonManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/person")
public class PersonEndpoint implements PersonEndpointDefinition {

    private final PersonManager personManager;

    public PersonEndpoint(PersonManager personManager) {
        this.personManager = personManager;
    }

    @Override
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(personManager.getAllPeople());
    }

    @Override
    @PostMapping(value = "/create")
    public ResponseEntity<CreatePersonResponse> createPerson(CreatePersonRequest request) {
        CreatePersonResponse response = new CreatePersonResponse();

        //Validate request
        Boolean validRequest = Validator.validate(request);
        if (!validRequest) {
            response.setFailReason("Invalid Email");
            return ResponseEntity.badRequest().body(response);
        }

        Person personToCreate = Person.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                    .build();

        response.setCreatedPerson(personManager.createPerson(personToCreate));

        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping(value = "/friendRequest")
    public ResponseEntity friendRequest(FriendRequest request) {
        personManager.friendRequest(request.getFromUsername(), request.getToUsername());
        return ResponseEntity.ok().build();
    }


}
