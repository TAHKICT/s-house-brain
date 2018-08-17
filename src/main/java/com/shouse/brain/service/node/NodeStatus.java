package com.shouse.brain.service.node;

import java.util.Arrays;

public enum  NodeStatus {
    SWITCHED_OFF (2000, "switched off"),
    ACTIVE (2001, "active"),
    AWAITING (2002, "awaiting");

    private int statusCode;
    private String description;

    NodeStatus(int statusCode, String description){
        this.statusCode = statusCode;
        this.description = description;
    }

    public static NodeStatus getNodeStatusByCode(int statusCode){
        return Arrays.stream(NodeStatus.values()).filter(nodeState -> nodeState.statusCode == statusCode).findFirst().get();
    }

    public int getStatusCode(){
        return statusCode;
    }
}
