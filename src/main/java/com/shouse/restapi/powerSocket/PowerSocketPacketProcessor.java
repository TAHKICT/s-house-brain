package com.shouse.restapi.powerSocket;

import com.shouse.node.PowerSocket.PowerSocketNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shouse.core.Common.NodeType;
import org.springframework.beans.factory.annotation.Autowired;
import shouse.core.communication.Packet;
import shouse.core.communication.PacketProcessor;
import shouse.core.controller.NodeContainer;

public class PowerSocketPacketProcessor implements PacketProcessor {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


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

        if(packet.getData().get("nodeTypeId").equals(String.valueOf(NodeType.POWER_SOCKET.getId()))) {
            log.info("isApplicable. true. packet: " + packet);
            return true;
        }

        log.info("isApplicable. false. packet: " + packet);
        return false;
    }
}
