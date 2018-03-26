package dao;

import domain.NodeInfo;
import service.NodeStatus;
import service.NodeType;
import service.NodeValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage tools.
 */
public class NodesDao {

    public static List<NodeInfo> getNodes(){
        List<NodeInfo> nodes = new ArrayList<>();

        nodes.add(new NodeInfo((long) 1,"0.0.0.0","", NodeStatus.SWITCHED_OFF.getStatusCode(), NodeType.LIGHT.getNodeTypeId(), NodeValue.OFF.getNodeValue()));
        nodes.add(new NodeInfo((long) 2,"0.0.0.0","", NodeStatus.SWITCHED_OFF.getStatusCode(), NodeType.LIGHT.getNodeTypeId(), NodeValue.OFF.getNodeValue()));
        nodes.add(new NodeInfo((long) 3,"0.0.0.0","", NodeStatus.SWITCHED_OFF.getStatusCode(), NodeType.POWER_SOCKET.getNodeTypeId(), NodeValue.OFF.getNodeValue()));

        return nodes;
    }
}
