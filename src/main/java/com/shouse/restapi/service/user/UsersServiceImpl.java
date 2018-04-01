package com.shouse.restapi.service.user;

import com.shouse.restapi.controllers.UsersWebSocketController;
import com.shouse.restapi.domain.NodeInfoExtended;
import com.shouse.restapi.service.node.NodeStatus;
import com.shouse.restapi.service.node.NodeType;
import com.shouse.restapi.service.node.NodesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UsersServiceImpl implements UsersService {

    UsersWebSocketController usersWebSocketController;

    @Autowired
    NodesService nodesService;

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
    public void setUsersWebSocketController(UsersWebSocketController usersWebSocketController) {
        this.usersWebSocketController = usersWebSocketController;
    }

    @Override
    public void handleNodeChange(int nodeId, String value) {
        usersWebSocketController.fireResponse(new UserResponse(nodeId, value));
    }

}
