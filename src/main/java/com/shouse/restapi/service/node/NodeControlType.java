package com.shouse.restapi.service.node;

import java.util.Arrays;

public enum NodeControlType {
    CHECKBOX(0, "checkbox"),
    RADIO(1, "radio"),
    INFORMATION(2, "information");

    private int id;
    private String description;

    NodeControlType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static NodeControlType getNodeControllTypeById(int id){
        return Arrays.stream(NodeControlType.values()).filter(controlType -> controlType.getId() == id).findFirst().get();
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
