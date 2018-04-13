package com.shouse.restapi.service.client;

public class ClientRequestGetNodes {

    private String type;
    private String name;
    private boolean activeOnly;

    public ClientRequestGetNodes() {
    }

    public ClientRequestGetNodes(String type, String name, boolean activeOnly) {
        this.type = type;
        this.name = name;
        this.activeOnly = activeOnly;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActiveOnly() {
        return activeOnly;
    }

    public void setActiveOnly(boolean activeOnly) {
        this.activeOnly = activeOnly;
    }
}
