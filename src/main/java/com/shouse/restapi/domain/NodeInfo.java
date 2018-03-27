package com.shouse.restapi.domain;

public class NodeInfo {

    private Long id;
    private int nodeTypeId;

    public NodeInfo(Long id, int nodeTypeId) {
        this.id = id;
        this.nodeTypeId = nodeTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(int nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }
}
