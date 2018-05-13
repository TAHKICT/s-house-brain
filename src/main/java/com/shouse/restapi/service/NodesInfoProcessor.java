package com.shouse.restapi.service;

import shouse.core.api.RequestProcessor;
import shouse.core.controller.NodeContainer;
import shouse.core.node.Node;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.request.RequestBody;
import shouse.core.node.response.Message;

import java.util.List;
import java.util.stream.Collectors;

import static shouse.core.node.response.ResponseStatus.FAILURE;
import static shouse.core.node.response.ResponseStatus.SUCCESS;

/**
 * Created by Maks on 13.05.2018.
 */
public class NodesInfoProcessor implements RequestProcessor {

    private NodeContainer storage;

    public NodesInfoProcessor(NodeContainer storage) {
        this.storage = storage;
    }

    @Override
    public Message processRequest(Request request) {
        RequestBody data = request.getBody();
        String command = data.getParameter("command");
        if (command != null){
            switch (command){
                case "nodeInfo":
                    return getAllNodesInfo();
            }
        }

        return new Message(FAILURE);
    }

    private Message getAllNodesInfo() {
        Message message = new Message();
        message.setStatus(SUCCESS);

        List<NodeInfo> nodes = storage.getAllNodes().stream().map(Node::getNodeInfo).collect(Collectors.toList());
        message.put("nodes", nodes);
        return message;
    }

    @Override
    public boolean isApplicable(Request request) {
        String type =  request.getBody().getParameter("type");
        return type != null && type.equals("general");
    }
}
