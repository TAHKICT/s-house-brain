package com.shouse.restapi.configs;

import com.shouse.restapi.service.NodesInfoProcessor;
import com.shouse.restapi.service.client.WebApplicationService;
import com.shouse.restapi.service.node.WebRequestsToNodeTools;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.shouse.restapi.communicators.service.NodesService;
import com.shouse.restapi.communicators.service.NodesServiceImpl;
import com.shouse.restapi.storage.InMemoryStorage;
import org.springframework.web.client.RestTemplate;
import shouse.core.api.Notifier;
import shouse.core.api.RequestDispatcher;
import shouse.core.api.RequestDispatcherImpl;
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
    public NodesService basicNodesService(RestTemplate restTemplate, NodeContainer nodeContainer) {
        return new NodesServiceImpl(restTemplate, nodeContainer);
    }

    @Bean
    public NodeStorage nodeStorage(List<Communicator> communicators, List<Notifier> notifiers){
        return new InMemoryStorage(communicators, notifiers);
    }

    @Bean
    public NodeContainer nodeContainer(NodeStorage nodeStorage){
        return new NodeContainer(nodeStorage);
    }

    @Bean
    public Controller smartController(Set<Communicator> communicators, Set<PacketProcessor> packetProcessors){
        return new ControllerImpl(communicators, packetProcessors);
    }

    @Bean
    public RequestDispatcher requestDispatcher(Set<RequestProcessor> processors){
        return new RequestDispatcherImpl(processors);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public NodesInfoProcessor nodesInfoProcessor(NodeContainer container){
        return new NodesInfoProcessor(container);
    }
}
