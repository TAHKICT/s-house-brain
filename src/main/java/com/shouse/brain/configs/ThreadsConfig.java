package com.shouse.brain.configs;

import com.shouse.brain.communication.NodesAliveChecking;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shouse.core.SmartHouseContext;
import shouse.core.controller.Controller;
import shouse.core.controller.NodeContainer;

@Configuration
public class ThreadsConfig {

    @Bean
    public NodesAliveChecking nodesAliveChecking (NodeContainer nodeContainer){
        return new NodesAliveChecking(nodeContainer, 10, 60);
    }

    @Bean
    public Controller smartController(SmartHouseContext context){
        return context.getController();
    }

}
