package com.shouse.restapi.storage;

import com.shouse.restapi.PowerSocket.PowerSocketNode;
import com.shouse.restapi.dummy.dummyNode.TestNode;
import com.shouse.restapi.service.node.NodeLocation;
import org.springframework.stereotype.Component;
import shouse.core.communication.Communicator;
import shouse.core.node.Node;
import shouse.core.node.storage.NodeStorage;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryStorage implements NodeStorage {

    private List<Communicator> communicators;

    public InMemoryStorage(List<Communicator> communicators) {
        this.communicators = communicators;
    }

    @Override
    public List<Node> loadNodes() {
        List<Node> nodes = new ArrayList<>();

        nodes.add(new PowerSocketNode(1, NodeLocation.KITCHEN.getId(),"стиральная машина", communicators.get(0)));
        nodes.add(new PowerSocketNode(2, NodeLocation.KITCHEN.getId(),"фен", communicators.get(0)));
        nodes.add(new PowerSocketNode(3, NodeLocation.KITCHEN.getId(),"возле шкафа", communicators.get(0)));
        nodes.add(new PowerSocketNode(4, NodeLocation.BEDROOM.getId(), "основной", communicators.get(0)));
        nodes.add(new PowerSocketNode(5, NodeLocation.BEDROOM.getId(), "ночной", communicators.get(0)));
        nodes.add(new PowerSocketNode(6, NodeLocation.BEDROOM.getId(),"возле зеркала", communicators.get(0)));
        nodes.add(new PowerSocketNode(7, NodeLocation.BEDROOM.getId(),"температура", communicators.get(0)));
        nodes.add(new PowerSocketNode(8, NodeLocation.BEDROOM.getId(),"влажность", communicators.get(0)));
        nodes.add(new PowerSocketNode(9, NodeLocation.BEDROOM.getId(),"температура", communicators.get(0)));
        nodes.add(new PowerSocketNode(10, NodeLocation.BEDROOM.getId(),"влажность", communicators.get(0)));

        return nodes;
    }
}
