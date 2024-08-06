package com.readyup.api.endpoint;

import com.readyup.api.request.AddToGroupRequest;
import com.readyup.api.request.CreateGroupRequest;
import com.readyup.api.response.GroupResponse;
import com.readyup.domain.Group;
import com.readyup.domain.Person;
import com.readyup.manager.definitions.GroupManager;

import com.readyup.security.jwt.JwtGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.readyup.api.endpointdefinition.GroupEndpointDefinition;

import java.util.List;

@RestController
@RequestMapping(path = "/api/groups")
public class GroupEndpoint implements GroupEndpointDefinition {

    private final JwtGenerator jwtGenerator;
    private final GroupManager groupManager;

    public GroupEndpoint(JwtGenerator jwtGenerator, GroupManager groupManager) {
        this.jwtGenerator = jwtGenerator;
        this.groupManager = groupManager;
    }

    @Override
    @PostMapping(value = "/create")
    public ResponseEntity<Boolean> create(String bearerToken, CreateGroupRequest request) {
        String username = jwtGenerator.getUsernameFromBearer(bearerToken);
        Boolean response = groupManager.create(request.getGroup(), username);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping(value = "/getGroupFor")
    public ResponseEntity<GroupResponse> getGroupFor(String bearerToken) {
        String username = jwtGenerator.getUsernameFromBearer(bearerToken);
        Person person = new Person();
        person.setUsername(username);

        Group response = groupManager.getGroupFor(person);
        return ResponseEntity.ok(new GroupResponse(response));
    }

    @Override
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupManager.getAllGroups());
    }

    @Override
    @PostMapping(value = "/addToGroup")
    public ResponseEntity<Group> addToGroup(AddToGroupRequest request) {
        groupManager.addMember(request.getUsername(), request.getGroupUid());
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = "/getJoinable")
    public ResponseEntity<List<Group>> getJoinableGroups(String bearerToken) {
        String username = jwtGenerator.getUsernameFromBearer(bearerToken);

        List<Group> groups = groupManager.getJoinableGroups(username);
        return ResponseEntity.ok(groups);
    }


}
