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
import shouse.core.SmartHouseContext;
import shouse.core.SmartHouseInitializer;
import shouse.core.api.Notifier;
import shouse.core.api.RequestDispatcher;
import shouse.core.api.RequestDispatcherImpl;
import shouse.core.api.RequestProcessor;
import shouse.core.communication.Communicator;
import shouse.core.communication.PacketProcessor;
import shouse.core.controller.Controller;
import shouse.core.controller.ControllerImpl;
import shouse.core.controller.NodeContainer;
import shouse.core.loader.NodeLoader;
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
    public NodeStorage nodeStorage(){
        return new InMemoryStorage();
    }

    @Bean
    public SmartHouseContext smartHouseContext(NodeStorage nodeStorage,
                                               Set<Communicator> communicators,
                                               Set<Notifier> notifiers){
        return new SmartHouseInitializer()
                .communicators(communicators)
                .notifiers(notifiers)
                .nodeStorage(nodeStorage)
                .initialize();
    }

    @Bean
    public NodeContainer nodeContainer(SmartHouseContext context){
        return context.getNodeContainer();
    }

    @Bean
    public Controller smartController(SmartHouseContext context){
        return context.getController();
    }

    @Bean
    public RequestDispatcher requestDispatcher(SmartHouseContext context){
        return context.getDispatcher();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public NodesInfoProcessor nodesInfoProcessor(SmartHouseContext context){
        NodesInfoProcessor nodesInfoProcessor = new NodesInfoProcessor(context.getNodeContainer());
        context.addRequestProcessor(nodesInfoProcessor);
        return nodesInfoProcessor;
    }
}
