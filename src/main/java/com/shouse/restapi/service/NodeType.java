package com.shouse.restapi.service;

import java.util.Arrays;

public enum NodeType {
    LIGHT (100, "light switch"),
    POWER_SOCKET (101, "power socket");

    private int nodeTypeId;
    private String nodeTypeDescription;

    NodeType(int nodeTypeId, String nodeTypeDescription) {
        this.nodeTypeId = nodeTypeId;
        this.nodeTypeDescription = nodeTypeDescription;
    }

    public NodeType getNodeTypeById(int nodeTypeId){
        return Arrays.stream(NodeType.values()).filter(nodeType -> nodeType.nodeTypeId == nodeTypeId).findFirst().get();
    }

    public int getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(int nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }

    public String getNodeTypeDescription() {
        return nodeTypeDescription;
    }

    public void setNodeTypeDescription(String nodeTypeDescription) {
        this.nodeTypeDescription = nodeTypeDescription;
    }
}
