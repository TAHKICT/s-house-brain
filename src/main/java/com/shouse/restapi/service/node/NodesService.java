package com.shouse.restapi.service.node;

import com.shouse.restapi.domain.NodeInfoExtended;

import java.util.Map;

/**
 * Holds logical part.
 */
public interface NodesService {

    /**
     * Only nodes, which exists in storage can be registered in system as active. The rest will bow out.
     * @param nodeId
     * @param ipAddress
     * @return the result message
     */
    String registerNode(String nodeId, String ipAddress);

    /**
     * When system starts, nodes map should be token from the storage.
     */
    void nodeInfoMapSynchronization();

    /**
     * Handle requests from nodes.
     * @param nodeId
     * @param value
     * @return
     */
    String handleNode(String nodeId, String value);

    /**
     *
     * @return map of actual node states
     */
    Map<Integer,NodeInfoExtended> getNodesMap();
}
