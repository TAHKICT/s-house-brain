package com.shouse.restapi.service.client;

public class ClientRequestGetNodes {

    private String type;
    private String name;

    public ClientRequestGetNodes() {
    }

    public ClientRequestGetNodes(String type, String name) {
        this.type = type;
        this.name = name;
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
}
