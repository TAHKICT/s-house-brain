package com.shouse.restapi.dummy.dummyNode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shouse.core.api.RequestProcessor;
import shouse.core.communication.PacketProcessor;

/**
 * Created by Maks on 11.05.2018.
 */
@Configuration
public class DummyConfig {

    @Bean
    public RequestProcessor dummyProcessor(){
        return new DummyProcessor();
    }

    @Bean
    public PacketProcessor dummyPacketProcessor(){
        return new DummyPacketProcessor();
    }

}
