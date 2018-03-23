package dao;

import domain.NodeInfo;
import service.NodeStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage tools.
 */
public class NodesDao {

    public static List<NodeInfo> getNodes(){
        List<NodeInfo> nodes = new ArrayList<>();

        nodes.add(new NodeInfo((long) 1,"0.0.0.0","", NodeStatus.SWITCHED_OFF.getStatusCode()));
        nodes.add(new NodeInfo((long) 2,"0.0.0.0","", NodeStatus.SWITCHED_OFF.getStatusCode()));
        nodes.add(new NodeInfo((long) 3,"0.0.0.0","", NodeStatus.SWITCHED_OFF.getStatusCode()));

        return nodes;
    }
}
