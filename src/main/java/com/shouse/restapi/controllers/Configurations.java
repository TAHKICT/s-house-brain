package com.shouse.restapi.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.shouse.restapi.service.NodesService;
import com.shouse.restapi.service.NodesServiceImpl;
import com.shouse.restapi.storage.InMemoryStorage;
import com.shouse.restapi.storage.NodesStorage;

@Configuration
public class Configurations {

    @Bean
    public NodesService basicNodesService() {
        return new NodesServiceImpl();
    }

    @Bean
    public NodesStorage nodesStorage() {
        return new InMemoryStorage();
    }
}
