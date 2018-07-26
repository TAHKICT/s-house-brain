package com.shouse.restapi.configs;

import com.shouse.restapi.service.NodesInfoProcessor;
import com.shouse.restapi.storage.InMemoryStorage;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import shouse.core.SmartHouseContext;
import shouse.core.SmartHouseInitializer;
import shouse.core.api.Notifier;
import shouse.core.api.RequestDispatcher;
import shouse.core.communication.Communicator;
import shouse.core.controller.Controller;
import shouse.core.controller.NodeContainer;
import shouse.core.node.storage.NodeStorage;

import java.util.Set;

@Configuration
public class BeansConfig {

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
