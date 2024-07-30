package com.readyup.api.endpointdefinition;

import com.readyup.api.request.AddToGroupRequest;
import com.readyup.api.request.CreateGroupRequest;
import com.readyup.api.response.GroupResponse;
import com.readyup.api.request.GetGroupForRequest;
import com.readyup.domain.Group;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "/api/groups",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public interface GroupEndpointDefinition {
    
    @ApiOperation(value = "Create a new group, error if exists")
    ResponseEntity<Boolean> create(@RequestBody CreateGroupRequest request);


    @ApiOperation(value = "Get group, error or null if not exist")
    ResponseEntity<GroupResponse> getGroupFor(@RequestBody GetGroupForRequest request);

    @ApiOperation(value = "Get all groups")
    ResponseEntity<List<Group>> getAllGroups();

    @ApiOperation(value = "Add a user to an existing group")
    ResponseEntity<Group> addToGroup(@RequestBody AddToGroupRequest request);


}
