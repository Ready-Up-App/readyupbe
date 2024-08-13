package com.readyup.api.endpointdefinition;

import com.readyup.api.request.RefreshTokenRequest;
import com.readyup.api.request.SignInRequest;
import com.readyup.api.request.SignUpRequest;
import com.readyup.api.response.RefreshTokenResponse;
import com.readyup.api.response.SignInResponse;
import com.readyup.api.response.SignUpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Api(value = "/api/auth",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public interface AuthEndpointDefinition {

    @ApiOperation(value = "signUp")
    ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request);

    @ApiOperation(value = "signIn")
    ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request);

    @ApiOperation(value = "getTokenHealth")
    ResponseEntity<Boolean> getTokenHealth(@RequestHeader String bearerToken);

}
