package com.shouse.restapi.service.node;

import com.shouse.restapi.domain.NodeInfoExtended;

import java.util.Map;

/**
 * Holds logical part.
 */
public interface NodesService {
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
     * @return map of actual node states
     */
    Map<Integer,NodeInfoExtended> getNodesMap();

    /**
     * Nodes regularly send alive requests. System should know when node is active or not.
     * @param nodeId
     * @return
     */
    String handleAliveRequestFromNode(String nodeId,  String ipAddress);
}
