package com.shouse.restapi.api.controllers;

import shouse.core.node.request.RequestBody;

/**
 * Created by Maks on 12.05.2018.
 */
public class WebRequest {

    private int nodeId;
    private RequestBody body;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public RequestBody getBody() {
        return body;
    }

    public void setBody(RequestBody body) {
        this.body = body;
    }
}
