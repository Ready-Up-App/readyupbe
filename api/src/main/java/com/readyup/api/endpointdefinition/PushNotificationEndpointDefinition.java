package com.readyup.api.endpointdefinition;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Api(value = "/api/auth",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public interface PushNotificationEndpointDefinition {
//    @ApiOperation(value = "setToken")
//    ResponseEntity setToken(@RequestHeader(name = "Authorization") String bearerToken, @RequestBody String pushToken);
//
//    @ApiOperation(value = "getToken")
//    ResponseEntity getToken(@RequestHeader(name = "Authorization") String bearerToken);

}
