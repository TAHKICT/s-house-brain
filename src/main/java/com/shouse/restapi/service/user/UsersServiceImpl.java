package com.shouse.restapi.service.user;

import com.shouse.restapi.domain.NodeInfoExtended;
import com.shouse.restapi.service.node.NodeStatus;
import com.shouse.restapi.service.node.NodesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UsersServiceImpl implements UsersService {

    @Autowired
    NodesService nodesService;

    @Override
    public List<NodeInfoExtended> getActiveNodes() {
        return nodesService.getNodesMap().values().stream().filter(e -> e.getNodeStatus().getStatusCode() == NodeStatus.ACTIVE.getStatusCode()).collect(Collectors.toList());
    }

    @Override
    public List<NodeInfoExtended> getAllNodes() {
        return nodesService.getNodesMap().values().stream().collect(Collectors.toList());
    }

    @Override
    public UserResponse handleUserRequest(UserRequest userRequest) {
        return null;
    }

}
