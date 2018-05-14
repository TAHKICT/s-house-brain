package com.shouse.restapi.dummy.dummyNode;

import com.shouse.restapi.service.node.NodeType;
import shouse.core.communication.Communicator;
import shouse.core.communication.Packet;
import shouse.core.node.Node;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.response.Message;

/**
 * Created by Maks on 10.05.2018.
 */
public class TestNode extends Node {

    private Communicator communicator;
    private int nodeLocationId;
    private String description;

    public TestNode(int id, int nodeLocationId, String description, Communicator communicator) {
        super(id, NodeType.POWER_SOCKET.getId());
        this.communicator = communicator;
        this.nodeLocationId = nodeLocationId;
        this.description = description;
    }

    @Override
    public NodeInfo getNodeInfo() {
        return new NodeInfo(getId(), getTypeId(), nodeLocationId, description);
    }

    @Override
    public Message process(Request request) {
        if (request.getBody().getParameter("aaaa") != null){
            communicator.sendPacket(createDeletePacket(request));
            return new Message();
        }

        throw new RuntimeException();
    }

    private Packet createDeletePacket(Request request) {
        return new Packet(getId());
    }

    @Override
    public void update(Packet packet) {

    }
}
