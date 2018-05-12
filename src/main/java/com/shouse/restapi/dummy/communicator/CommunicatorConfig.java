package com.shouse.restapi.dummy.communicator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shouse.core.communication.Communicator;

/**
 * Created by Maks on 12.05.2018.
 */
@Configuration
public class CommunicatorConfig {

    @Bean
    public Communicator dummyCommunicator(){
        return new DummyCommunicator();
    }

}
