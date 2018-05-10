package com.shouse.restapi.dummy;

import shouse.core.api.RequestProcessor;
import shouse.core.node.request.NodeRequest;
import shouse.core.node.response.Message;

/**
 * Created by Maks on 11.05.2018.
 */
public class DummyProcessor implements RequestProcessor {
    @Override
    public Message processRequest(NodeRequest nodeRequest) {
        System.out.println(nodeRequest.toString());
        return new Message();
    }

    @Override
    public boolean isApplicable(NodeRequest nodeRequest) {
        return true;
    }
}
