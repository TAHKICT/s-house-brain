package com.shouse.restapi.service.node;

import java.util.Arrays;

public enum NodeLocation {
    KITCHEN(0, "kitchen"),
    BEDROOM(1, "bedroom"),
    HALL(2, "hall"),
    BATHROOM(3, "bathroom");

    private int id;
    private String description;

    NodeLocation(int id, String description){
        this.id = id;
        this.description = description;
    }

    public static NodeLocation getNodeCategoryById(int nodeCategoryId){
        return Arrays.stream(NodeLocation.values()).filter(nodeLocation -> nodeLocation.getId() == nodeCategoryId).findFirst().get();
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

    public void setDescription(String description) {
        this.description = description;
    }
}
