package com.shouse.restapi.storage;

import com.shouse.node.powerSocket.PowerSocketNode;
import org.springframework.stereotype.Component;
import shouse.core.api.Notifier;
import shouse.core.communication.Communicator;
import shouse.core.node.Node;
import shouse.core.node.NodeLocation;
import shouse.core.node.storage.NodeStorage;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryStorage implements NodeStorage {

    private List<Communicator> communicators;
    private List<Notifier> notifiers;

    public InMemoryStorage(List<Communicator> communicators, List<Notifier> notifiers) {
        this.communicators = communicators;
        this.notifiers = notifiers;
    }

    @Override
    public List<Node> loadNodes() {
        NodeLocation kitchen = new NodeLocation(0, "Kitchen");

        List<Node> nodes = new ArrayList<>();

        nodes.add(new PowerSocketNode(1, kitchen,"стиральная машина", communicators.get(0),notifiers));
        nodes.add(new PowerSocketNode(2, kitchen,"фен", communicators.get(0),notifiers));
        nodes.add(new PowerSocketNode(3, kitchen,"возле шкафа", communicators.get(0),notifiers));
        nodes.add(new PowerSocketNode(4, kitchen,"основной", communicators.get(0),notifiers));
        nodes.add(new PowerSocketNode(5, kitchen,"ночной", communicators.get(0),notifiers));
        nodes.add(new PowerSocketNode(6, kitchen,"возле зеркала", communicators.get(0),notifiers));
        nodes.add(new PowerSocketNode(7, kitchen,"температура", communicators.get(0),notifiers));

        return nodes;
    }
}
