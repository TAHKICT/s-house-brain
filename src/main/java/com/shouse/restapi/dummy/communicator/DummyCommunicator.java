package com.shouse.restapi.dummy.communicator;

import shouse.core.communication.Communicator;
import shouse.core.communication.Packet;

/**
 * Created by Maks on 11.05.2018.
 */
public class DummyCommunicator implements Communicator {
    @Override
    public void sendPacket(Packet packet) {

    }

    @Override
    public Packet recivePacket() {
        return null;
    }

    @Override
    public boolean hasNewPacket() {
        return false;
    }
}
