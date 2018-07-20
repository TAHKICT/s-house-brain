package com.shouse.restapi.dummy.dummyNode;

import shouse.core.api.RequestProcessor;
import shouse.core.node.request.Request;
import shouse.core.node.response.Response;

/**
 * Created by Maks on 11.05.2018.
 */
public class DummyProcessor implements RequestProcessor {

    @Override
    public Response processRequest(Request request) {
        System.out.println(request.toString());
        return new Response();
    }

    @Override
    public boolean isApplicable(Request request) {
        return request.getNodeId() == 1;
    }
}
