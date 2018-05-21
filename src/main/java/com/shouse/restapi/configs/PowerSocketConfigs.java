package com.shouse.restapi.configs;

import com.shouse.node.powerSocket.PowerSocketPacketProcessor;
import com.shouse.node.powerSocket.PowerSocketRequestProcessor;
import com.shouse.restapi.communicators.PowerSocketWiFiCommunicator;
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
    public Communicator powerSocketWiFiCommunicator(RestTemplate restTemplate){
        return new PowerSocketWiFiCommunicator(restTemplate);
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
