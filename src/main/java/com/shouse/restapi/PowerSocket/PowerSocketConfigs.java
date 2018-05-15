package com.shouse.restapi.PowerSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import shouse.core.api.RequestProcessor;
import shouse.core.communication.Communicator;
import shouse.core.communication.PacketProcessor;
import shouse.core.controller.NodeContainer;

@Configuration
public class PowerSocketConfigs {

    @Bean
    public Communicator powerSocketWiFiCommunicator(RestTemplate restTemplate, NodeContainer nodeContainer){
        return new PowerSocketWiFiCommunicator(restTemplate, nodeContainer);
    }

    @Bean
    public PacketProcessor powerSocketPacketProcessor(NodeContainer nodeContainer){
        return new PowerSocketPacketProcessor(nodeContainer);
    }

    @Bean
    public RequestProcessor powerSocketRequestProcessor(NodeContainer nodeContainer){
        return new PowerSocketRequestProcessor(nodeContainer);
    }
}
