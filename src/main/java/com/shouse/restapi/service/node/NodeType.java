package com.shouse.restapi.service.node;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public enum NodeType {
    LIGHT (100, "light"),
    POWER_SOCKET (101, "Power socket"),
    SENSOR(102, "Sensor");

    private int id;
    private String name;

    NodeType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static NodeType getNodeTypeById(int nodeTypeId){
        return Arrays.stream(NodeType.values()).filter(nodeType -> nodeType.id == nodeTypeId).findFirst().get();
    }

    public static NodeType getNodeTypeByDescription(String description){
        return Arrays.stream(NodeType.values()).filter(nodeType -> nodeType.name.equals(description)).findFirst().get();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static List<String> getNames(){
        return Arrays.asList(values()).stream().map(e -> e.name).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "NodeType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
