package com.shouse.restapi.service.node;

public enum NodeValue {
    OFF ("on"),
    ON ("off");

    private String nodeValue;

    NodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getNodeValue() {
        return nodeValue;
    }
}
