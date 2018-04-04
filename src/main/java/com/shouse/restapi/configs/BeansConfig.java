package com.shouse.restapi.configs;

import com.shouse.restapi.service.user.UsersService;
import com.shouse.restapi.service.user.UsersServiceImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.shouse.restapi.service.node.NodesService;
import com.shouse.restapi.service.node.NodesServiceImpl;
import com.shouse.restapi.storage.InMemoryStorage;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfig {

    @Bean
    public NodesService basicNodesService() {
        return new NodesServiceImpl(new InMemoryStorage());
    }

    @Bean
    public UsersService usersService(){
        return new UsersServiceImpl();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

//    @Bean
//    public NodesStorage nodesStorage() {
//        return new InMemoryStorage();
//    }
}
