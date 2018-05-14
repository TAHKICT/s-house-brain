package com.shouse.restapi.PowerSocket;

import shouse.core.communication.Packet;
import shouse.core.communication.PacketProcessor;

public class PowerSocketPacketProcessor implements PacketProcessor {
    @Override
    public void processPacket(Packet request) {

    }

    @Override
    public boolean isApplicable(Packet request) {
        return false;
    }
}
