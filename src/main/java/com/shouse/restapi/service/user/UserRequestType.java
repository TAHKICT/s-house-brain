package com.shouse.restapi.service.user;

public enum UserRequestType {
    GET ("get"),
    SET ("set");

    private String requestType;

    UserRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }
}
