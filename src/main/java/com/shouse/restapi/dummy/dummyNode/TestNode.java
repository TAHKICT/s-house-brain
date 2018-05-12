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
public class TestNode implements Node {

    private Communicator communicator;
    private int id;
    private int nodeTypeId;
    private int nodeLocationId;
    private String description;

    public TestNode(int id, int nodeLocationId, String description, Communicator communicator) {
        this.communicator = communicator;
        this.id = id;
        this.nodeTypeId = NodeType.POWER_SOCKET.getId();
        this.nodeLocationId = nodeLocationId;
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public NodeInfo getNodeInfo() {
        return null;
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
        return new Packet(id);
    }

    @Override
    public void update(Packet packet) {

    }
}
