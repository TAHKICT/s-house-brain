package com.shouse.restapi.PowerSocket;

import shouse.core.api.RequestProcessor;
import shouse.core.node.request.Request;
import shouse.core.node.response.Message;

public class PowerSocketRequestProcessor implements RequestProcessor {
    @Override
    public Message processRequest(Request request) {
        return null;
    }

    @Override
    public boolean isApplicable(Request request) {
        return false;
    }
}
