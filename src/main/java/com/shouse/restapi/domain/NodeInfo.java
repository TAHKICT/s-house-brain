package com.shouse.restapi.domain;

import com.shouse.restapi.service.node.NodeControlType;
import com.shouse.restapi.service.node.NodeLocation;
import com.shouse.restapi.service.node.NodeType;

public class NodeInfo {

    private int id;
    private int nodeTypeId;
    private int nodeLocationId;
    private int nodeControlTypeId;
    private String description;

    public NodeInfo(int id, int nodeTypeId) {
        this.id = id;
        this.nodeTypeId = nodeTypeId;
    }

    public NodeInfo(int id, int nodeTypeId, int nodeLocationId, int nodeControlTypeId, String description) {
        this.id = id;
        this.nodeTypeId = nodeTypeId;
        this.nodeLocationId = nodeLocationId;
        this.nodeControlTypeId = nodeControlTypeId;
        this.description = description;
    }

    public NodeInfo(int id, NodeType nodeType, NodeLocation nodeLocation, NodeControlType nodeControlType, String description) {
        this.id = id;
        this.nodeTypeId = nodeType.getId();
        this.nodeLocationId = nodeLocation.getId();
        this.nodeControlTypeId = nodeControlType.getId();
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

    public int getNodeLocationId() {
        return nodeLocationId;
    }

    public void setNodeLocationId(int nodeLocationId) {
        this.nodeLocationId = nodeLocationId;
    }

    public int getNodeControlTypeId() {
        return nodeControlTypeId;
    }

    public void setNodeControlTypeId(int nodeControlTypeId) {
        this.nodeControlTypeId = nodeControlTypeId;
    }

    @Override
    public String toString() {
        return "NodeInfo{" +
                "id=" + id +
                ", nodeTypeId=" + nodeTypeId +
                ", nodeLocationId=" + nodeLocationId +
                ", nodeControlTypeId=" + nodeControlTypeId +
                ", description='" + description + '\'' +
                '}';
    }
}
