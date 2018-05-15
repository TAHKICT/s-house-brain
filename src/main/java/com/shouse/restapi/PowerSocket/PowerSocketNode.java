package com.shouse.restapi.PowerSocket;

import com.shouse.restapi.service.node.NodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shouse.core.communication.Communicator;
import shouse.core.communication.Packet;
import shouse.core.node.Node;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.response.Response;
import shouse.core.node.response.ResponseStatus;

public class PowerSocketNode extends Node {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Communicator communicator;
    private int nodeLocationId;
    private String description;
    private String ip;
    private boolean isSwitched;

    public PowerSocketNode(int id, int nodeLocationId, String description, Communicator communicator) {
        super(id,  NodeType.POWER_SOCKET.getId());
        this.nodeLocationId = nodeLocationId;
        this.description = description;
        this.communicator = communicator;
        log.info("PowerSocketNode created");
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isSwitched() {
        return isSwitched;
    }

    public void setSwitched(boolean switched) {
        isSwitched = switched;
    }

    @Override
    public NodeInfo getNodeInfo() {
        return new NodeInfo(getId(),getTypeId(), nodeLocationId, description);
    }

    @Override
    public Response process(Request request) {
        Response response = new Response();
        response.setRequest(request);

        if(request.getBody().getParameter("switch").equals("checked"))
            setSwitched(true);
        else if(request.getBody().getParameter("switch").equals("unchecked"))
            setSwitched(false);
        else {
            log.error("processRequest. Request processing fail.");
            response.setStatus(ResponseStatus.FAILURE);
            response.put("message", "Parameter value is wrong.");
            return null;
        }

        response.setStatus(ResponseStatus.SUCCESS);
        response.put("execution status", "in progress");

        return response;
    }

    @Override
    public void update(Packet packet) {

    }
}
