package com.shouse.restapi.service.node;

import com.shouse.restapi.communicators.service.NodeInfoExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Deprecated
public class WebRequestsToNodeTools {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    public String sendRequest(NodeInfoExtended node, String request){
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl("http://" + node.getIpAddress() + "/command-from-server")
                .queryParam("switch", request);

        log.info("sendRequest. url: " + url.toUriString());

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url.toUriString(), String.class);

        String response = responseEntity.getBody().toString();
        log.info("sendRequest. " +
                "nodeId:" + node.getId() + ", request:" + request + ". " +
                "Response: " + response);

        return response;
    }
}
