package com.shouse.restapi.PowerSocket;

import shouse.core.Common.NodeType;
import org.springframework.beans.factory.annotation.Autowired;
import shouse.core.communication.Packet;
import shouse.core.communication.PacketProcessor;
import shouse.core.controller.NodeContainer;

public class PowerSocketPacketProcessor implements PacketProcessor {

    private NodeContainer nodeContainer;

    @Autowired
    public PowerSocketPacketProcessor(NodeContainer nodeContainer) {
        this.nodeContainer = nodeContainer;
    }

    @Override
    public void processPacket(Packet packet) {
        PowerSocketNode node = (PowerSocketNode) nodeContainer.getNode(packet.getNodeId()).get();
        node.processPacket(packet);
    }

    @Override
    public boolean isApplicable(Packet packet) {
        if(packet.getData().get("nodeTypeId").equals(NodeType.POWER_SOCKET.getId()))
            return true;

        return false;
    }
}
