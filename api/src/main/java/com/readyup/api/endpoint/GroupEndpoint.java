package com.readyup.api.endpoint;

import com.readyup.api.request.AddToGroupRequest;
import com.readyup.api.request.CreateGroupRequest;
import com.readyup.api.response.GroupResponse;
import com.readyup.api.request.GetGroupForRequest;
import com.readyup.domain.Group;
import com.readyup.domain.Person;
import com.readyup.manager.definitions.AuthManager;
import com.readyup.manager.definitions.GroupManager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.readyup.api.endpointdefinition.GroupEndpointDefinition;

import java.util.List;

@RestController
@RequestMapping(path = "/api/groups")
public class GroupEndpoint implements GroupEndpointDefinition {

    private final GroupManager groupManager;

    public GroupEndpoint(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @Override
    @PostMapping(value = "/create")
    public ResponseEntity<Boolean> create(CreateGroupRequest request) {
        Boolean response = groupManager.create(request.getGroup(), request.getRequesterUsername());
        return ResponseEntity.ok(response);
    }

    @Override
//    @PostMapping(value = "/update")
    public ResponseEntity<Boolean> update(Group request) {
        Boolean response = groupManager.update(request);
        return ResponseEntity.ok(response);
    }

    @Override
//    @PostMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(Group request) {
        Boolean response = groupManager.delete(request);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping(value = "/getGroupFor")
    public ResponseEntity<GroupResponse> getGroupFor(GetGroupForRequest request) {
        Person person = new Person();
        person.setUsername(request.getUsername());

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
        System.out.print(bearerToken);
//        authManager.getAuthenticationManager().
//        groupManager.getJoinable()
        return null;
    }


}
