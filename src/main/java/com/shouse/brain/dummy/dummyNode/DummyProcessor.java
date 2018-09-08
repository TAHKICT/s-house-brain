package com.shouse.brain.dummy.dummyNode;

import shouse.core.api.RequestProcessor;
import shouse.core.common.SystemConstants;
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
        return request.getBody().getParameter(SystemConstants.nodeId) != null;
    }
}
