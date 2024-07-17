package com.readyup.api.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readyup.api.endpointdefinition.GroupsEndpointDefinition;

@RestController
@RequestMapping(path = "/api/groups")
public class GroupsEndpoint implements GroupsEndpointDefinition{

    @Override
    @GetMapping(value = "/api/groups/get")
    public ResponseEntity<Boolean> test() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
     
}
