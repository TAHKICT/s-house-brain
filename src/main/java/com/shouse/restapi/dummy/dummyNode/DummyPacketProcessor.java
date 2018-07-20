package com.shouse.restapi.dummy.dummyNode;

import shouse.core.communication.Packet;
import shouse.core.communication.PacketProcessor;

/**
 * Created by Maks on 12.05.2018.
 */
public class DummyPacketProcessor implements PacketProcessor {
    @Override
    public void processPacket(Packet packet) {

    }

    @Override
    public boolean isApplicable(Packet packet) {

        return false;
    }
}
