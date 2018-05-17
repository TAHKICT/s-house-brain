package com.shouse.restapi.configs;

import com.shouse.restapi.notifires.RestWebApiNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import shouse.core.api.Notifier;

@Configuration
public class NotifierConfig {

    @Bean
    public Notifier restWebApiNotifier(RestTemplate restTemplate){
        return new RestWebApiNotifier(restTemplate);
    }

}

