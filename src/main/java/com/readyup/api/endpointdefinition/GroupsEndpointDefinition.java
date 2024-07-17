package com.readyup.api.endpointdefinition;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/api/groups",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public interface GroupsEndpointDefinition {
    
    @ApiOperation(value = "Test")
    ResponseEntity<Boolean> test();
}
