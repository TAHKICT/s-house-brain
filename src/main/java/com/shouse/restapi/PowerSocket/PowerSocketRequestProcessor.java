package com.shouse.restapi.PowerSocket;

import shouse.core.Common.NodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import shouse.core.api.RequestProcessor;
import shouse.core.controller.NodeContainer;
import shouse.core.node.request.Request;
import shouse.core.node.response.Response;

public class PowerSocketRequestProcessor implements RequestProcessor {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private NodeContainer nodeContainer;

    @Autowired
    public PowerSocketRequestProcessor(NodeContainer nodeContainer) {
        this.nodeContainer = nodeContainer;
        log.info("PowerSocketRequestProcessor created");
    }

    @Override
    public Response processRequest(Request request) {
        PowerSocketNode node = (PowerSocketNode) nodeContainer.getNode(Integer.parseInt(request.getBody().getParameter("nodeId"))).get();
        return node.process(request);
    }

    @Override
    public boolean isApplicable(Request request) {
        if(request.getBody().getParameter("nodeTypeId").equals(NodeType.POWER_SOCKET.getId()))
            return true;

        return false;
    }
}
