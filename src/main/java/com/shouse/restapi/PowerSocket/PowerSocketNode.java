package com.shouse.restapi.PowerSocket;

import com.shouse.restapi.service.node.NodeType;
import shouse.core.communication.Communicator;
import shouse.core.communication.Packet;
import shouse.core.node.Node;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.response.Message;

public class PowerSocketNode extends Node {

    private Communicator communicator;
    private int nodeLocationId;
    private String description;
    private String ip;

    public PowerSocketNode(int id, int nodeLocationId, String description, Communicator communicator) {
        super(id,  NodeType.POWER_SOCKET.getId());
        this.nodeLocationId = nodeLocationId;
        this.description = description;
        this.communicator = communicator;
    }

    public Communicator getCommunicator() {
        return communicator;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public NodeInfo getNodeInfo() {
        return new NodeInfo(getId(),getTypeId(), nodeLocationId, description);
    }

    @Override
    public Message process(Request request) {
        return null;
    }

    @Override
    public void update(Packet packet) {

    }
}
