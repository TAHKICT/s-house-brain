package com.shouse.restapi.service.node;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum NodeLocation {
    KITCHEN(0, "Kitchen"),
    BEDROOM(1, "Bedroom"),
    HALL(2, "Hall"),
    BATHROOM(3, "Bathroom");

    private int id;
    private String name;

    NodeLocation(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static NodeLocation getNodeLocationById(int nodeCategoryId){
        return Arrays.stream(NodeLocation.values()).filter(nodeLocation -> nodeLocation.getId() == nodeCategoryId).findFirst().get();
    }

    public static NodeLocation getNodeLocationByName(String name){
        return Arrays.stream(NodeLocation.values()).filter(nodeLocation -> nodeLocation.getName().equals(name)).findFirst().get();
    }

    public static List<String> getNames(){
        return Arrays.asList(values()).stream().map(e -> e.name).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
