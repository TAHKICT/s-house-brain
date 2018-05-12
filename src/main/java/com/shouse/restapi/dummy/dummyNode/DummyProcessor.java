package com.shouse.restapi.dummy.dummyNode;

import shouse.core.api.RequestProcessor;
import shouse.core.node.request.Request;
import shouse.core.node.response.Message;

/**
 * Created by Maks on 11.05.2018.
 */
public class DummyProcessor implements RequestProcessor {

    @Override
    public Message processRequest(Request request) {
        System.out.println(request.toString());
        return new Message();
    }

    @Override
    public boolean isApplicable(Request request) {
        return true;
    }
}
