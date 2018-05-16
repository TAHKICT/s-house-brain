package com.shouse.restapi.dummy.dummyNode;

import shouse.core.Common.NodeType;
import shouse.core.communication.Communicator;
import shouse.core.communication.Packet;
import shouse.core.node.Node;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.response.Response;

/**
 * Created by Maks on 10.05.2018.
 */
public class TestNode extends Node {

    private Communicator communicator;
    private int nodeLocationId;
    private String description;

    public TestNode(int id, int nodeLocationId, String description, Communicator communicator) {
        super(id, NodeType.LIGHT.getId(), false);
        this.communicator = communicator;
        this.nodeLocationId = nodeLocationId;
        this.description = description;
    }

    @Override
    public NodeInfo getNodeInfo() {
        return new NodeInfo(getId(), getTypeId(), nodeLocationId, description);
    }

    @Override
    public Response process(Request request) {
        if (request.getBody().getParameter("aaaa") != null){
            communicator.sendPacket(createDeletePacket(request));
            return new Response();
        }

        throw new RuntimeException();
    }

    private Packet createDeletePacket(Request request) {
        return new Packet(getId());
    }

    @Override
    public void processPacket(Packet packet) {

    }
}
