package service;

import dao.NodesDao;
import domain.NodeInfo;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class holds logical part.
 */
public class NodesService {

    private static Map<Long, NodeInfo> nodeInfoMap;
    private boolean isSynchronized = false;

    public static String registerNode(String nodeId, String ipAddress){
        if(nodeInfoMap.containsKey(nodeId))
            return "registered";
        else
            return "node not found";
    }

    public static void nodeInfoMapSynchronization(){
        nodeInfoMap = NodesDao.getNodes().stream().collect(
                Collectors.toMap(NodeInfo::getId, NodeInfo -> NodeInfo)
        );
    }
}
