package com.shouse.restapi.service.user;

public class UserRequest {

    private int nodeId;
    private String value;

    public UserRequest(int nodeId, String value) {
        this.nodeId = nodeId;
        this.value = value;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
