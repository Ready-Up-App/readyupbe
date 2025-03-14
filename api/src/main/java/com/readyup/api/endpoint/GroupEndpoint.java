package com.readyup.api.endpoint;

import com.readyup.api.request.CreateGroupRequest;
import com.readyup.api.request.DeleteGroupRequest;
import com.readyup.api.request.GetCurrentGroupRequest;
import com.readyup.api.request.JoinGroupRequest;
import com.readyup.api.response.GroupResponse;
import com.readyup.domain.Group;
import com.readyup.manager.definitions.GroupManager;

import com.readyup.ri.entity.UserGroupEntity;
import com.readyup.security.jwt.JwtGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.readyup.api.endpointdefinition.GroupEndpointDefinition;

import java.util.List;

@RestController
@RequestMapping(path = "/api/groups")
@CrossOrigin
public class GroupEndpoint implements GroupEndpointDefinition {

    private final JwtGenerator jwtGenerator;
    private final GroupManager groupManager;

    public GroupEndpoint(JwtGenerator jwtGenerator, GroupManager groupManager) {
        this.jwtGenerator = jwtGenerator;
        this.groupManager = groupManager;
    }

    @Override
    @PostMapping(value = "/create")
    public ResponseEntity create(String bearerToken, CreateGroupRequest request) {
        //validate request
        if (request.getGroup() == null) {
            throw new RuntimeException("Group must not be null");
        }
        if (request.getGroup().getName() == null || request.getGroup().getName().isEmpty()) {
            throw new RuntimeException("group/id must not be null or empty");
        }

        String username = jwtGenerator.getUsernameFromBearer(bearerToken);
        if (!username.equals(request.getUser().getUsername())) {
            throw new RuntimeException("User requesting is not the same as the provisioned token");
        }
        groupManager.create(request.getUser(), request.getGroup());
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(String bearerToken, DeleteGroupRequest request) {
        //validate request
        if (request.getGroup() == null) {
            throw new RuntimeException("Group must not be null");
        }
        if (request.getGroup().getId() == null || request.getGroup().getId().isEmpty()) {
            throw new RuntimeException("group/id must not be null or empty");
        }

        String username = jwtGenerator.getUsernameFromBearer(bearerToken);
        if (!username.equals(request.getUser().getUsername())) {
            throw new RuntimeException("User requesting is not the same as the provisioned token");
        }


        groupManager.delete(request.getUser(), request.getGroup());
        return ResponseEntity.ok(true);
    }

    @Override
    @GetMapping(value = "/getCurrentGroup")
    public ResponseEntity<GroupResponse> getCurrentGroup(String bearerToken) {
        String username = jwtGenerator.getUsernameFromBearer(bearerToken);

        Group response = groupManager.getCurrentGroup(username);
        return ResponseEntity.ok(new GroupResponse(response));
    }

    @Override
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupManager.getAllGroups());
    }

    @Override
    @PostMapping(value = "/joinGroup")
    public ResponseEntity<Group> joinGroup(String bearerToken, JoinGroupRequest request) {
        String username = jwtGenerator.getUsernameFromBearer(bearerToken);

        groupManager.addMember(username, request.getGroupId());
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = "/getJoinable")
    public ResponseEntity<List<Group>> getJoinableGroups(String bearerToken) {
        String username = jwtGenerator.getUsernameFromBearer(bearerToken);

        List<Group> groups = groupManager.getJoinableGroups(username);
        return ResponseEntity.ok(groups);
    }

    @Override
    @GetMapping(value = "/leaveGroup")
    public ResponseEntity<Boolean> leaveGroup(String bearerToken) {
        String username = jwtGenerator.getUsernameFromBearer(bearerToken);
        Boolean leftGroup;
        try{
            leftGroup = groupManager.leaveGroup(username);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(false);
        }

        return ResponseEntity.ok(leftGroup);
    }
}
