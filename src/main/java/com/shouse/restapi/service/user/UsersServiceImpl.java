package com.shouse.restapi.service.user;

import com.shouse.restapi.domain.NodeInfoExtended;
import com.shouse.restapi.service.node.NodeStatus;
import com.shouse.restapi.service.node.NodeType;
import com.shouse.restapi.service.node.NodesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsersServiceImpl implements UsersService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NodesService nodesService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<NodeInfoExtended> getActiveNodes() {
        return nodesService.getNodesMap().values().stream().filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()).collect(Collectors.toList());
    }

    @Override
    public List<NodeInfoExtended> getActiveNodes(NodeType nodeType) {
        return nodesService.getNodesMap().values().stream()
                .filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()
                && e.getNodeTypeId() == nodeType.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<NodeInfoExtended> getAllNodes() {
        return nodesService.getNodesMap().values().stream().collect(Collectors.toList());
    }

    @Override
    public UserResponse handleUserRequest(UserRequest userRequest) {
        NodeInfoExtended currentNode = getActiveNodes().stream().filter(node -> node.getId() == userRequest.getNodeId()).findFirst().get();

        if(userRequest.getValue().isEmpty())
            return new UserResponse(userRequest.getNodeId(),currentNode.getValue());
        else {
            currentNode.setValue(userRequest.getValue());
            return new UserResponse(userRequest.getNodeId(), currentNode.getValue());
        }
    }

    @Override
    public void handleNodeChange(int nodeId, String value) {
        final String uri = "http://localhost:8282";
        String message =  "{\"nodeId\":" + nodeId + ",\"value\":" + value + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(message, headers);

        ResponseEntity<String> response = restTemplate.exchange(uri+"/web-rest-api/for-core-application", HttpMethod.POST, entity, String.class);
        log.info("UsersServiceImpl. handleNodeChange. " +
                "nodeId:" + nodeId + ", value:" + value + ". " +
                "Response: " + response);
    }

}
