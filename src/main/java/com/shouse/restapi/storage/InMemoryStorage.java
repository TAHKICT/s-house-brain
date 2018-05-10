package com.shouse.restapi.storage;

import com.shouse.restapi.domain.NodeInfo;
import com.shouse.restapi.service.node.NodeControlType;
import com.shouse.restapi.service.node.NodeLocation;
import com.shouse.restapi.service.node.NodeType;


import java.util.ArrayList;
import java.util.List;

public class InMemoryStorage implements NodesStorage {

    @Override
    public List<NodeInfo> getNodes() {
        List<NodeInfo> nodes = new ArrayList<>();

        nodes.add(new NodeInfo(1, NodeType.POWER_SOCKET, NodeLocation.KITCHEN, NodeControlType.CHECKBOX,"стиральная машина"));
        nodes.add(new NodeInfo(2, NodeType.POWER_SOCKET, NodeLocation.KITCHEN, NodeControlType.CHECKBOX,"фен"));
        nodes.add(new NodeInfo(3, NodeType.POWER_SOCKET, NodeLocation.HALL, NodeControlType.CHECKBOX,"возле шкафа"));
        nodes.add(new NodeInfo(4, NodeType.LIGHT, NodeLocation.BEDROOM, NodeControlType.CHECKBOX, "основной"));
        nodes.add(new NodeInfo(5, NodeType.LIGHT, NodeLocation.BEDROOM, NodeControlType.CHECKBOX, "ночной"));
        nodes.add(new NodeInfo(6, NodeType.LIGHT, NodeLocation.BEDROOM, NodeControlType.CHECKBOX,"возле зеркала"));
        nodes.add(new NodeInfo(7, NodeType.LIGHT, NodeLocation.HALL, NodeControlType.INFORMATION,"температура"));
        nodes.add(new NodeInfo(8, NodeType.LIGHT, NodeLocation.HALL, NodeControlType.INFORMATION,"влажность"));
        nodes.add(new NodeInfo(9, NodeType.LIGHT, NodeLocation.KITCHEN, NodeControlType.INFORMATION,"температура"));
        nodes.add(new NodeInfo(10, NodeType.LIGHT, NodeLocation.KITCHEN, NodeControlType.INFORMATION,"влажность"));

        return nodes;
    }
}
