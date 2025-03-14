package com.readyup.api.endpointdefinition;

import com.readyup.api.request.CreateGroupRequest;
import com.readyup.api.request.DeleteGroupRequest;
import com.readyup.api.request.GetCurrentGroupRequest;
import com.readyup.api.request.JoinGroupRequest;
import com.readyup.api.response.GroupResponse;
import com.readyup.domain.Group;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Api(value = "/api/groups",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public interface GroupEndpointDefinition {

    @ApiOperation(value = "Create a new group, error if exists")
    ResponseEntity create(@RequestHeader(name = "Authorization") String bearerToken, @RequestBody CreateGroupRequest request);

    @ApiOperation(value = "Delete users group, error if exists")
    ResponseEntity<Boolean> delete(@RequestHeader(name = "Authorization")String bearerToken, @RequestBody DeleteGroupRequest deleteRequest);

    @ApiOperation(value = "Get group, error or null if not exist")
    ResponseEntity<GroupResponse> getCurrentGroup(@RequestHeader(name = "Authorization") String bearerToken);

    @ApiOperation(value = "Get all groups")
    ResponseEntity<List<Group>> getAllGroups();

    @ApiOperation(value = "Add a user to an existing group")
    ResponseEntity<Group> joinGroup(@RequestHeader(name = "Authorization") String bearerToken, @RequestBody JoinGroupRequest request);

    @ApiOperation(value = "Get joinable groups")
    ResponseEntity<List<Group>> getJoinableGroups(@RequestHeader(name = "Authorization") String bearerToken);

    @ApiOperation(value = "Leave current group")
    ResponseEntity<Boolean> leaveGroup(@RequestHeader(name = "Authorization") String bearerToken);
}
