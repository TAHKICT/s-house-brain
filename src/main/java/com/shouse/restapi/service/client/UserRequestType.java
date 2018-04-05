package com.shouse.restapi.service.client;

public enum UserRequestType {
    GET ("get"),
    SET ("set");

    private String text;

    UserRequestType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
