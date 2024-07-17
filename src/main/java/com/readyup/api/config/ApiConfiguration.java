package com.readyup.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.readyup.api.endpoint.GroupsEndpoint;
import com.readyup.api.endpointdefinition.GroupsEndpointDefinition;

@Configuration
public class ApiConfiguration {
    
    @Bean
    public GroupsEndpointDefinition groupsEndpoint() {
        return new GroupsEndpoint();
    }
}
