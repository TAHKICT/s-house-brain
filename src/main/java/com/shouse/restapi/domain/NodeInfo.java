package com.shouse.restapi.domain;

public class NodeInfo {

    private int id;
    private int nodeTypeId;
    private String description;

    public NodeInfo(int id, int nodeTypeId) {
        this.id = id;
        this.nodeTypeId = nodeTypeId;
    }

    public NodeInfo(int id, int nodeTypeId, String description) {
        this.id = id;
        this.nodeTypeId = nodeTypeId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(int nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
