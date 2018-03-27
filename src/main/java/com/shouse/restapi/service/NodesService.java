package com.shouse.restapi.service;

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
}
