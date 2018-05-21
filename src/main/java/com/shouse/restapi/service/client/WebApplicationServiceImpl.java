//package com.shouse.restapi.service.client;
//
//import com.shouse.restapi.communicators.service.NodesService;
//import com.shouse.restapi.communicators.service.NodeInfoExtended;
//import com.shouse.restapi.service.Messages;
//import com.shouse.restapi.service.node.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class WebApplicationServiceImpl implements WebApplicationService {
//
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    NodesService nodesService;
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Autowired
//    WebRequestsToNodeTools webRequestsToNodeTools;
//
//    @Override
//    public List<String> getMenuSortTypes() {
//        return ClientMenuSortType.getTypeNames();
//    }
//
//    @Override
//    public List<NodeInfoExtended> getNodes(ClientRequestGetNodes clientRequestGetNodes) {
//        if (clientRequestGetNodes.getType().equals(ClientMenuSortType.TYPE.getName()))
//            return nodesService.getNodesMap().values().stream()
//                    .filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()
//                            && e.getNodeTypeName() == NodeType.getNodeTypeByDescription(clientRequestGetNodes.getName()).getId())
//                    .collect(Collectors.toList());
//        else if(clientRequestGetNodes.getType().equals(ClientMenuSortType.LOCATION.getName()))
//            return nodesService.getNodesMap().values().stream()
//                    .filter(e -> {
//                        if(clientRequestGetNodes.isActiveOnly() && e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()
//                                && e.getNodeLocation() == NodeLocation.getNodeLocationByName(clientRequestGetNodes.getName()).getId())
//                            return true;
//
//                        if(!clientRequestGetNodes.isActiveOnly() && e.getNodeLocation() == NodeLocation.getNodeLocationByName(clientRequestGetNodes.getName()).getId())
//                            return true;
//
//                        return false;
//                    })
//                    .collect(Collectors.toList());
//
//        log.error("getActiveNodes. clientRequestGetNodes.getType()=" + clientRequestGetNodes.getType() + " don't match.");
//        return null;
//    }
//
//    @Override
//    public List<NodeInfoExtended> getActiveNodes() {
//        return nodesService.getNodesMap().values().stream().filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<NodeInfoExtended> getActiveNodes(ClientRequestGetNodes clientRequestGetNodes) {
//        if (clientRequestGetNodes.getType().equals(ClientMenuSortType.TYPE.getName()))
//            return nodesService.getNodesMap().values().stream()
//                    .filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()
//                            && e.getNodeTypeName() == NodeType.getNodeTypeByDescription(clientRequestGetNodes.getName()).getId())
//                    .collect(Collectors.toList());
//        else if(clientRequestGetNodes.getType().equals(ClientMenuSortType.LOCATION.getName()))
//            return nodesService.getNodesMap().values().stream()
//                    .filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()
//                            && e.getNodeLocation() == NodeLocation.getNodeLocationByName(clientRequestGetNodes.getName()).getId())
//                    .collect(Collectors.toList());
//
//        log.error("getActiveNodes. clientRequestGetNodes.getType()=" + clientRequestGetNodes.getType() + " don't match.");
//        return null;
//    }
//
//    @Override
//    public List<NodeInfoExtended> getActiveNodes(NodeType nodeType) {
//        log.info("WebApplicationServiceImpl. getActiveNodes. " + nodeType);
//        System.out.println(nodesService.getNodesMap());
//        return nodesService.getNodesMap().values().stream()
//                .filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()
//                && e.getNodeTypeName() == nodeType.getId())
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<NodeInfoExtended> getAllNodes() {
//        return nodesService.getNodesMap().values().stream().collect(Collectors.toList());
//    }
//
//    @Override
//    public ResponseForWebClient handleRequestFromClient(NodeParamChangeEvent nodeParamChangeEvent) {
//        log.info("WebApplicationServiceImpl. handleRequestFromClient. " + nodeParamChangeEvent);
//
//        NodeInfoExtended currentNode = getActiveNodes().stream().filter(node -> node.getId() == nodeParamChangeEvent.getNodeId()).findFirst().get();
//        webRequestsToNodeTools.sendRequest(currentNode, nodeParamChangeEvent.getValue());
//        nodesService.getNodesMap().get(nodeParamChangeEvent.getNodeId()).setValue(nodeParamChangeEvent.getValue());
//
//        return new ResponseForWebClient(nodeParamChangeEvent.getNodeId(), currentNode.getValue());
//    }
//
//    @Override
//    public void sendNodeChangeRequestToClient(int nodeId, String value) {
//        final String uri = "http://localhost:8282";
//        ResponseEntity<String> response = restTemplate.postForEntity(uri+"/web-rest-api/for-core-application",new NodeParamChangeEvent(nodeId,value), String.class);
//
//        log.info("WebApplicationServiceImpl. sendNodeChangeRequestToClient. " +
//                "nodeId:" + nodeId + ", value:" + value + ". " +
//                "Response: " + response);
//    }
//
//}
