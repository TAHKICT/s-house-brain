package com.shouse.restapi.configs;

import com.shouse.restapi.service.client.WebApplicationService;
import com.shouse.restapi.service.client.WebApplicationServiceImpl;
import com.shouse.restapi.service.node.WebRequestsToNodeTools;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.shouse.restapi.communicator.controllers.service.NodesService;
import com.shouse.restapi.communicator.controllers.service.NodesServiceImpl;
import com.shouse.restapi.storage.InMemoryStorage;
import org.springframework.web.client.RestTemplate;
import shouse.core.api.RequestProcessor;
import shouse.core.communication.Communicator;
import shouse.core.communication.PacketProcessor;
import shouse.core.controller.Controller;
import shouse.core.controller.ControllerImpl;
import shouse.core.controller.NodeContainer;
import shouse.core.node.storage.NodeStorage;

import java.util.List;
import java.util.Set;

@Configuration
public class BeansConfig {

    @Bean
    public NodesService basicNodesService(WebApplicationService webAppService, RestTemplate restTemplate, NodeContainer nodeContainer) {
        return new NodesServiceImpl(restTemplate, webAppService, nodeContainer);
    }

    @Bean
    public NodeStorage nodeStorage(List<Communicator> communicators){
        return new InMemoryStorage(communicators);
    }

    @Bean
    public NodeContainer nodeContainer(NodeStorage nodeStorage){
        return new NodeContainer(nodeStorage);
    }

    @Bean
    public Controller smartController(Set<Communicator> communicators, Set<RequestProcessor> processors, Set<PacketProcessor> packetProcessors){
        return new ControllerImpl(communicators, processors, packetProcessors);
    }

    @Bean
    public WebApplicationService usersService(){
        return new WebApplicationServiceImpl();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public WebRequestsToNodeTools webRequestsToNodeTools() {
        return new WebRequestsToNodeTools();
    }

//    @Bean
//    public NodesStorage nodesStorage() {
//        return new InMemoryStorage();
//    }
}
