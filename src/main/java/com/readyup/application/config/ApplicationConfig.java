package com.readyup.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.readyup.api.config.ApiConfiguration;

@Configuration
@Import({ApiConfiguration.class})
public class ApplicationConfig {
    
}
