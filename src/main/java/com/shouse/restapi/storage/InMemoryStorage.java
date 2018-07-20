package com.shouse.restapi.storage;

//import com.shouse.node.powerSocket.PowerSocketNode;
import org.springframework.stereotype.Component;
import shouse.core.api.Notifier;
import shouse.core.communication.Communicator;
import shouse.core.loader.NodeFactory;
import shouse.core.node.Node;
import shouse.core.node.NodeLocation;
import shouse.core.node.storage.NodeStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryStorage implements NodeStorage {

    @Override
    public List<Node> loadNodes(Map<String, NodeFactory> blueprints) {
        NodeLocation kitchen = new NodeLocation(0, "Kitchen");

        NodeFactory nodeFactory = blueprints.get("powerSocket");
        Node node = nodeFactory.createNode(new HashMap<>());

        List<Node> nodes = new ArrayList<>();
        nodes.add(node);

//        nodes.add(new PowerSocketNode(1, kitchen,"стиральная машина", communicators.get(0),notifiers));
//        nodes.add(new PowerSocketNode(2, kitchen,"фен", communicators.get(0),notifiers));
//        nodes.add(new PowerSocketNode(3, kitchen,"возле шкафа", communicators.get(0),notifiers));
//        nodes.add(new PowerSocketNode(4, kitchen,"основной", communicators.get(0),notifiers));
//        nodes.add(new PowerSocketNode(5, kitchen,"ночной", communicators.get(0),notifiers));
//        nodes.add(new PowerSocketNode(6, kitchen,"возле зеркала", communicators.get(0),notifiers));
//        nodes.add(new PowerSocketNode(7, kitchen,"температура", communicators.get(0),notifiers));

        return nodes;
    }
}
