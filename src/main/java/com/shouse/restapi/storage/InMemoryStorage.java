package com.shouse.restapi.storage;

import com.shouse.restapi.domain.NodeInfoExtended;
import com.shouse.restapi.service.NodeStatus;


import java.util.ArrayList;
import java.util.List;

public class InMemoryStorage implements NodesStorage {

    @Override
    public List<NodeInfoExtended> getNodes() {
        List<NodeInfoExtended> nodes = new ArrayList<>();

        nodes.add(new NodeInfoExtended((long) 1,100,"", "", NodeStatus.SWITCHED_OFF,""));
        nodes.add(new NodeInfoExtended((long) 2,100,"", "", NodeStatus.SWITCHED_OFF,""));
        nodes.add(new NodeInfoExtended((long) 3,101,"", "", NodeStatus.SWITCHED_OFF,""));

        return nodes;
    }
}
