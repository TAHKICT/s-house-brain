package com.shouse.restapi.dummy;

import shouse.core.communication.Communicator;
import shouse.core.communication.Packet;
import shouse.core.node.Node;
import shouse.core.node.request.NodeRequest;
import shouse.core.node.response.Message;

/**
 * Created by Maks on 10.05.2018.
 */
public class TestNode implements Node {

    private Communicator communicator;

    public TestNode(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public Message process(NodeRequest request) {
        if (request.getBody().getParameter("aaaa") != null){
            communicator.sendPacket(createDeletePacket(request));
            return new Message();
        }

        throw new RuntimeException();
    }

    private Packet createDeletePacket(NodeRequest request) {
        return () -> null;
    }

    @Override
    public void update(Packet packet) {

    }
}
