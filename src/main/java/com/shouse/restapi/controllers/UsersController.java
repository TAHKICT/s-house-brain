package com.shouse.restapi.controllers;

import com.shouse.restapi.domain.NodeInfoMessage;
import com.shouse.restapi.service.node.NodeType;
import com.shouse.restapi.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    @RequestMapping("/user/getactivenodes")
    public List<NodeInfoMessage> getActiveNodes() {
        return usersService.getActiveNodes().stream().map(nodeInfoExtended -> nodeInfoExtended.getNodeInfoMessage()).collect(Collectors.toList());
    }

    @RequestMapping("/user/getactivenodes/{nodeTypeDescription}")
    public List<NodeInfoMessage> getActiveNodesByType(@PathVariable(value="nodeTypeDescription") String nodeTypeDescription) {
        return usersService.getActiveNodes(NodeType.getNodeTypeByDescription(nodeTypeDescription)).stream().map(nodeInfoExtended -> nodeInfoExtended.getNodeInfoMessage()).collect(Collectors.toList());
    }

}
