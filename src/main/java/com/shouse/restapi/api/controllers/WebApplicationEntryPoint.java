package com.shouse.restapi.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import shouse.core.api.Notifier;
import shouse.core.api.RequestDispatcher;
import shouse.core.node.request.Request;
import shouse.core.node.response.Response;



@RestController
@RequestMapping("/core-rest-api/for-web-application")
public class WebApplicationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private RequestDispatcher requestDispatcher;

    private RestTemplate restTemplate;

    public WebApplicationEntryPoint(RequestDispatcher requestDispatcher, RestTemplate restTemplate) {
        this.requestDispatcher = requestDispatcher;
        this.restTemplate = restTemplate;
    }

    /**
     * One entry point for every request.
     * @param webRequest
     * @return
     */
    @RequestMapping(value = "/entry-point", method = RequestMethod.POST)
    public Response apiEntryPoint(@RequestBody WebRequest webRequest){
        Request request = createRequest(webRequest);
        request.setNotifier(defineNotifier());
        return requestDispatcher.dispatchRequest(request);
    }

    //TODO: get notifier
    private Notifier defineNotifier() {
        return (response) -> {
            restTemplate.postForEntity("http://localhost:8181/web-rest-api/response", response, Response.class);
        };
    }

    private Request createRequest(WebRequest requestFromClient) {
        Request request = new Request();
        request.setBody(requestFromClient.getBody());
        return request;
    }
}
