package com.shouse.restapi.service.user;

import com.shouse.restapi.domain.NodeInfoExtended;
import com.shouse.restapi.service.node.NodeType;

import java.util.List;

public interface UsersService {

    public List<NodeInfoExtended> getActiveNodes();

    public List<NodeInfoExtended> getActiveNodes(NodeType nodeType);

    public List<NodeInfoExtended> getAllNodes();

    public UserResponse handleUserRequest(UserRequest userRequest);

    public void handleNodeChange(int nodeId, String value);
}
