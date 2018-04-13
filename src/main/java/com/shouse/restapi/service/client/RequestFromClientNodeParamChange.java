package com.shouse.restapi.service.client;

public class RequestFromClientNodeParamChange {

    private int nodeId;
    private String value;

    public RequestFromClientNodeParamChange() {}

    public RequestFromClientNodeParamChange(int nodeId, String value) {
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

    @Override
    public String toString() {
        return "RequestFromClientNodeParamChange{" +
                "nodeId=" + nodeId +
                ", value='" + value + '\'' +
                '}';
    }
}
