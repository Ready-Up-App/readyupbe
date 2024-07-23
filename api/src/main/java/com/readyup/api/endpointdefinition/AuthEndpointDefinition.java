package com.readyup.api.endpointdefinition;

import com.readyup.api.request.SignInRequest;
import com.readyup.api.request.SignUpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "/api/auth",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public interface AuthEndpointDefinition {

    @ApiOperation(value = "signUp")
    ResponseEntity<String> signUp(@RequestBody SignUpRequest request);

    @ApiOperation(value = "signIn")
    ResponseEntity<String> signIn(@RequestBody SignInRequest request);
}
