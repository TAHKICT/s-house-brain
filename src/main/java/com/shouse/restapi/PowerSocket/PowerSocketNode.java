package com.shouse.restapi.PowerSocket;

import shouse.core.Common.NodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shouse.core.Common.SystemConstants;
import shouse.core.api.Notifier;
import shouse.core.communication.Communicator;
import shouse.core.communication.Packet;
import shouse.core.node.Node;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.request.RequestIdGenerator;
import shouse.core.node.response.ExecutionStatus;
import shouse.core.node.response.Response;
import shouse.core.node.response.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class PowerSocketNode extends Node {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Communicator communicator;
    private int nodeLocationId;
    private String description;
    private boolean isSwitched;
    private Map<String,Notifier> requestsMap = new HashMap ();

    public PowerSocketNode(int id, int nodeLocationId, String description, Communicator communicator) {
        super(id,  NodeType.POWER_SOCKET.getId(), false);
        this.nodeLocationId = nodeLocationId;
        this.description = description;
        this.communicator = communicator;
        log.info("PowerSocketNode created");
    }

    public boolean isSwitched() {
        return isSwitched;
    }

    public void setSwitched(boolean switched) {
        isSwitched = switched;
    }

    @Override
    public NodeInfo getNodeInfo() {
        return new PowerSocketNodeInfo(getId(),getTypeId(), nodeLocationId, description, isActive(), isSwitched);
    }

    @Override
    public Response process(Request request) {
        Response response = new Response();

        if(!isActive()){
            log.error("process. Request processing fail. Parameter value is wrong.");
            response.setStatus(ResponseStatus.FAILURE);
            response.put(SystemConstants.failureMessage, "Node is not active.");
            return response;
        }

        if(request.getBody().getParameter("switch").equals("checked"))
            setSwitched(true);
        else if(request.getBody().getParameter("switch").equals("unchecked"))
            setSwitched(false);
        else {
            log.error("process. Request processing fail. Parameter value is wrong.");
            response.setStatus(ResponseStatus.FAILURE);
            response.put(SystemConstants.failureMessage, "Parameter value is wrong.");
            return response;
        }

        String requestId = String.valueOf(RequestIdGenerator.generateId());
        requestsMap.put(requestId, request.getNotifier());

        response.setStatus(ResponseStatus.SUCCESS);
        response.put(SystemConstants.executionStatus, ExecutionStatus.IN_PROGRESS);
        response.put(SystemConstants.requestId, requestId);

        Packet packet = new Packet(getId());
        packet.putData("switch", String.valueOf(isSwitched()));
        packet.putData(SystemConstants.requestId, requestId);
        communicator.sendPacket(packet);

        return response;
    }

    @Override
    public void processPacket(Packet packet) {

        if(packet.getData().get(SystemConstants.nodeTaskStatus).equals("executed")){
            String requestId = packet.getData().get(SystemConstants.requestId);
            Notifier notifier =  requestsMap.get(requestId);
            if(notifier != null) {
                requestsMap.remove(requestId);
                log.info(String.format("processPacket. Request with id: {} successfully executed by node", requestId));

                Response response = new Response(ResponseStatus.SUCCESS);
                response.put(SystemConstants.executionStatus, ExecutionStatus.READY);
                response.put(SystemConstants.requestId, requestId);
                notifier.sendResponse(response);
            }
        }

    }
}
