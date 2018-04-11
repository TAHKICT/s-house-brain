package com.shouse.restapi.controllers;

import com.shouse.restapi.domain.NodeInfoMessage;
import com.shouse.restapi.service.client.ClientRequestGetNodes;
import com.shouse.restapi.service.node.NodeType;
import com.shouse.restapi.service.client.ClientRequest;
import com.shouse.restapi.service.client.ClientResponse;
import com.shouse.restapi.service.client.ClientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/core-rest-api/for-web-application")
public class WebApplicationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ClientsService clientsService;

    @RequestMapping("/get-menu-sort-types")
    public List<String> getMenuSortTypes(){
        return clientsService.getMenuSortTypes();
    }

    @RequestMapping("/get-menu-items/{sortType}")
    public List<String> getNamesOfMenuItems(@PathVariable(value="sortType") String sortType,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        return clientsService.getNamesOfMenuItems(sortType);
    }

    @RequestMapping("/get-active-nodes")
    public List<NodeInfoMessage> getActiveNodes(@RequestBody ClientRequestGetNodes clientRequestGetNodes,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return clientsService.getActiveNodes(clientRequestGetNodes).stream().map(nodeInfoExtended -> nodeInfoExtended.getNodeInfoMessage()).collect(Collectors.toList());
    }

    @RequestMapping("/get-active-nodes/{nodeTypeDescription}")
    public List<NodeInfoMessage> getActiveNodesByType(
            @PathVariable(value="nodeTypeDescription") String nodeTypeDescription,
            HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.info("WebApplicationController. getActiveNodesByType. nodeTypeDescription:" + nodeTypeDescription);
        return clientsService.getActiveNodes(NodeType.getNodeTypeByDescription(nodeTypeDescription)).stream().map(nodeInfoExtended -> nodeInfoExtended.getNodeInfoMessage()).collect(Collectors.toList());
    }

    @RequestMapping("/web-application-event")
    public ClientResponse handleRequestFromWebApplication(@RequestBody ClientRequest clientRequest) {
        log.info("WebApplicationController. handleRequestFromWebApplication. :" + clientRequest);
        return clientsService.handleRequestFromClient(clientRequest);
    }




}
