package com.shouse.restapi.service.node;

import com.shouse.restapi.domain.NodeInfo;
import com.shouse.restapi.domain.NodeInfoExtended;
import com.shouse.restapi.service.Messages;
import com.shouse.restapi.service.client.WebApplicationService;
import com.shouse.restapi.storage.NodesStorage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

public class NodesServiceImpl implements NodesService {

    @Autowired
    private WebApplicationService webApplicationService;

    private NodesStorage nodesStorage;
    private static Map<Integer, NodeInfoExtended> nodeInfoMap;
    private static boolean isSynchronized = false;

    public NodesServiceImpl(NodesStorage nodesStorage) {
        this.nodesStorage = nodesStorage;
    }

    @Override
    public String handleNode(String nodeId, String value) {
        if(nodeId == null)
            return Messages.nodeIdFormatIsNull;

        if(!validNodeId(nodeId))
            return Messages.nodeIdFormatIsNotValid;

        int id = Integer.valueOf(nodeId);

        if (!nodeInfoMap.containsKey(id))
            return Messages.nodeNotFound;
        else if(nodeInfoMap.get(id).getNodeStatus().getStatusCode() == NodeStatus.SWITCHED_OFF.getStatusCode())
            return Messages.nodeIsNotActive;

        nodeInfoMap.get(id).setValue(value);
        webApplicationService.sendNodeChangeRequestToClient(id,value);

        return Messages.nodeHandledSuccessfully;
    }

    public Map<Integer, NodeInfoExtended> getNodesMap() {
        return nodeInfoMap;
    }

    @Override
    public String handleAliveRequestFromNode(String nodeId, String ipAddress) {
        if(nodeId == null)
            return Messages.nodeIdFormatIsNull;

        if(!validNodeId(nodeId))
            return Messages.nodeIdFormatIsNotValid;

        if(!validIP(ipAddress))
            return String.format(Messages.nodeIPAddressIsNotValid, ipAddress);

        int id = Integer.valueOf(nodeId);

        if(nodeInfoMap.containsKey(id)) {
            nodeInfoMap.get(id).setIpAddress(ipAddress);
            nodeInfoMap.get(id).setNodeStatus(NodeStatus.ACTIVE);
            return Messages.nodeRegistered;
        } else
            return Messages.nodeNotFound;
    }

    private static boolean validNodeId(String nodeId){
        try {
            Integer.valueOf(nodeId);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private static boolean validIP (String ip) {
        try {
            if ( ip == null || ip.isEmpty() ) {
                return false;
            }

            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
                return false;
            }

            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            if ( ip.endsWith(".") ) {
                return false;
            }

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    @PostConstruct
    public void nodeInfoMapSynchronization(){
        nodeInfoMap = nodesStorage.getNodes().stream().collect(
                Collectors.toMap(NodeInfo::getId, NodeInfo -> new NodeInfoExtended(NodeInfo.getId(),NodeInfo.getNodeTypeId(),NodeInfo.getNodeLocationId(),NodeInfo.getNodeControlTypeId(),NodeInfo.getDescription()))
        );
        isSynchronized = true;
    }
}
