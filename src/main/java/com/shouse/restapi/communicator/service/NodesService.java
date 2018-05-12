package com.shouse.restapi.communicator.service;

import shouse.core.communication.Packet;

import java.util.Map;

/**
 * Holds logical part.
 */
public interface NodesService {

    void handleNode(Packet packet);

    /**
     * Handle requests from nodes.
     * @param nodeId
     * @param value
     * @return
     */
    @Deprecated
    String handleNode(String nodeId, String value);

    /**
     * @return map of actual node states
     */
    @Deprecated
    Map<Integer,NodeInfoExtended> getNodesMap();

    /**
     * Nodes regularly send alive requests. System should know when node is active or not.
     * @param nodeId
     * @return
     */
    String handleAliveRequestFromNode(String nodeId,  String ipAddress);
}
