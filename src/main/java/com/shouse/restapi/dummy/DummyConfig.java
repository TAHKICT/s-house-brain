package com.shouse.restapi.dummy;

import com.shouse.restapi.dummy.communicator.DummyCommunicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shouse.core.api.RequestProcessor;
import shouse.core.communication.Communicator;

/**
 * Created by Maks on 11.05.2018.
 */
@Configuration
public class DummyConfig {

    @Bean
    public Communicator dummyCommunicator(){
        return new DummyCommunicator();
    }

    @Bean
    public RequestProcessor dummyProcessor(){
        return new DummyProcessor();
    }

}
