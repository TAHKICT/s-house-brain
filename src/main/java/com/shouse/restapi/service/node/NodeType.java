package com.shouse.restapi.service.node;

import java.util.Arrays;

public enum NodeType {
    LIGHT (100, "light"),
    POWER_SOCKET (101, "power-socket"),
    SENSOR(102, "sensor");

    private int id;
    private String description;

    NodeType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static NodeType getNodeTypeById(int nodeTypeId){
        return Arrays.stream(NodeType.values()).filter(nodeType -> nodeType.id == nodeTypeId).findFirst().get();
    }

    public static NodeType getNodeTypeByDescription(String description){
        return Arrays.stream(NodeType.values()).filter(nodeType -> nodeType.description.equals(description)).findFirst().get();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setNodeTypeDescription(String nodeTypeDescription) {
        this.description = nodeTypeDescription;
    }

    @Override
    public String toString() {
        return "NodeType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
