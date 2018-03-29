package com.shouse.restapi.service;

import com.shouse.restapi.domain.NodeInfoExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shouse.restapi.storage.NodesStorage;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

public class NodesServiceImpl implements NodesService{

    private NodesStorage nodesStorage;
    private static Map<Long, NodeInfoExtended> nodeInfoMap;
    private static boolean isSynchronized = false;

    public NodesServiceImpl(NodesStorage nodesStorage) {
        this.nodesStorage = nodesStorage;
    }

    public String registerNode(String nodeId, String ipAddress){
        if(nodeId(nodeId) == null)
            return Messages.nodeIdFormatIsNotValid;

            Long id = nodeId(nodeId);
            if(nodeInfoMap.containsKey(id)) {

                if(!validIP(ipAddress))
                    return String.format(Messages.nodeIPAddressIsNotValid, ipAddress);

                nodeInfoMap.get(id).setIpAddress(ipAddress);
                nodeInfoMap.get(id).setNodeStatus(NodeStatus.ACTIVE);
                return Messages.nodeRegistered;
            } else
                return Messages.nodeNotFound;
    }

    public String handleNode(String nodeId, String value) {
        if (!nodeInfoMap.containsKey(nodeId))
            return Messages.nodeNotFound;
        else if(nodeInfoMap.get(nodeId).getNodeStatus().getStatusCode() == NodeStatus.SWITCHED_OFF.getStatusCode())
            return Messages.nodeIsNotActive;

        nodeInfoMap.get(nodeId).setValue(value);

        return Messages.nodeHandledSuccessfully;
    }

    private static Long nodeId(String nodeIdString){
        try {
            return Long.parseLong(nodeIdString);
        }catch (NumberFormatException ex){
            return null;
        }
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
                Collectors.toMap(NodeInfoExtended::getId, NodeInfoExtended -> NodeInfoExtended)
        );

        isSynchronized = true;
    }
}
