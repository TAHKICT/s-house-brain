package com.shouse.restapi.api.notifires;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import shouse.core.api.Notifier;
import shouse.core.node.response.Response;

public class RestWebApiNotifier implements Notifier{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate;

    public RestWebApiNotifier(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendResponse(Response response) {
        LOGGER.info("Send response to web api: ".concat(response.toString()));

        try {
            restTemplate.postForEntity("http://localhost:8282/web-rest-api/for-core-application/entry-point", response, Response.class);
        }
        catch (ResourceAccessException rae){
            LOGGER.error(rae.getMessage());
            LOGGER.error("No access to REST API server!");
        }
    }

}
