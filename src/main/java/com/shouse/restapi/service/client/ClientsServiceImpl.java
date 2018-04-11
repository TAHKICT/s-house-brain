package com.shouse.restapi.service.client;

import com.shouse.restapi.domain.NodeInfoExtended;
import com.shouse.restapi.service.Messages;
import com.shouse.restapi.service.node.NodeLocation;
import com.shouse.restapi.service.node.NodeStatus;
import com.shouse.restapi.service.node.NodeType;
import com.shouse.restapi.service.node.NodesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class ClientsServiceImpl implements ClientsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NodesService nodesService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<String> getMenuSortTypes() {
        return ClientMenuSortType.getTypeNames();
    }

    @Override
    public List<String> getNamesOfMenuItems(String sortType) {
        if(sortType.equals(ClientMenuSortType.TYPE.getName()))
            return NodeType.getNames();

        if(sortType.equals(ClientMenuSortType.LOCATION.getName()))
            return NodeLocation.getNames();

        log.error(String.format(Messages.incorrectMenuSortTypeName, sortType));
        return null;
    }

    @Override
    public List<NodeInfoExtended> getActiveNodes() {
        return nodesService.getNodesMap().values().stream().filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()).collect(Collectors.toList());
    }

    @Override
    public List<NodeInfoExtended> getActiveNodes(ClientRequestGetNodes clientRequestGetNodes) {
        if (clientRequestGetNodes.getType().equals(ClientMenuSortType.TYPE.getName()))
            return nodesService.getNodesMap().values().stream()
                    .filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()
                            && e.getNodeTypeId() == NodeType.getNodeTypeByDescription(clientRequestGetNodes.getName()).getId())
                    .collect(Collectors.toList());
        else if(clientRequestGetNodes.getType().equals(ClientMenuSortType.LOCATION.getName()))
            return nodesService.getNodesMap().values().stream()
                    .filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()
                            && e.getNodeLocationId() == NodeLocation.getNodeLocationByName(clientRequestGetNodes.getName()).getId())
                    .collect(Collectors.toList());

        log.error("getActiveNodes. clientRequestGetNodes.getType()=" + clientRequestGetNodes.getType() + " don't match.");
        return null;
    }

    @Override
    public List<NodeInfoExtended> getActiveNodes(NodeType nodeType) {
        log.info("ClientsServiceImpl. getActiveNodes. " + nodeType);
        System.out.println(nodesService.getNodesMap());
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
    public ClientResponse handleRequestFromClient(ClientRequest clientRequest) {
        log.info("ClientsServiceImpl. handleRequestFromClient. " + clientRequest);
        NodeInfoExtended currentNode = getActiveNodes().stream().filter(node -> node.getId() == clientRequest.getNodeId()).findFirst().get();
        nodesService.getNodesMap().get(clientRequest.getNodeId()).setValue(clientRequest.getValue());
        System.out.println(nodesService.getNodesMap());
        return new ClientResponse(clientRequest.getNodeId(), currentNode.getValue());
//        if(clientRequest.getValue().isEmpty())
//            return new ClientResponse(clientRequest.getNodeId(),currentNode.getValue());
//        else {
//            currentNode.setValue(clientRequest.getValue());
//            return new ClientResponse(clientRequest.getNodeId(), currentNode.getValue());
//        }
    }

    @Override
    public void sendNodeChangeRequestToClient(int nodeId, String value) {
        final String uri = "http://localhost:8282";
        String message =  "{\"nodeId\":" + nodeId + ",\"value\":" + value + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(message, headers);

        ResponseEntity<String> response = restTemplate.exchange(uri+"/web-rest-api/for-core-application", HttpMethod.POST, entity, String.class);
        log.info("ClientsServiceImpl. sendNodeChangeRequestToClient. " +
                "nodeId:" + nodeId + ", value:" + value + ". " +
                "Response: " + response);
    }

}
