package com.shouse.restapi.notifires;

import org.springframework.web.client.RestTemplate;
import shouse.core.api.Notifier;
import shouse.core.node.response.Response;

public class RestWebApiNotifier implements Notifier{

    private RestTemplate restTemplate;

    public RestWebApiNotifier(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendResponse(Response response) {
        restTemplate.postForEntity("http://localhost:8181/web-rest-api/response", response, Response.class);
    }

}
