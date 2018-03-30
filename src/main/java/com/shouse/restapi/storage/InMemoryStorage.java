package com.shouse.restapi.storage;

import com.shouse.restapi.domain.NodeInfo;
import com.shouse.restapi.domain.NodeInfoExtended;
import com.shouse.restapi.service.node.NodeStatus;
import com.shouse.restapi.service.node.NodeType;


import java.util.ArrayList;
import java.util.List;

public class InMemoryStorage implements NodesStorage {

    @Override
    public List<NodeInfo> getNodes() {
        List<NodeInfo> nodes = new ArrayList<>();

        nodes.add(new NodeInfo(1, NodeType.POWER_SOCKET.getNodeTypeId(),"In the kitchen"));
        nodes.add(new NodeInfo(2, NodeType.POWER_SOCKET.getNodeTypeId(),"In the hall"));
        nodes.add(new NodeInfo(3, NodeType.LIGHT.getNodeTypeId(),"In the bedroom"));

        return nodes;
    }
}
