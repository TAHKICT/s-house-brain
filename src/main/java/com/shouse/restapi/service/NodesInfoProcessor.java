package com.shouse.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import shouse.core.api.RequestProcessor;
import shouse.core.controller.NodeContainer;
import shouse.core.node.Node;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.request.RequestBody;
import shouse.core.node.response.Response;

import java.util.List;
import java.util.stream.Collectors;

import static shouse.core.node.response.ResponseStatus.FAILURE;
import static shouse.core.node.response.ResponseStatus.SUCCESS;

/**
 * Created by Maks on 13.05.2018.
 */
public class NodesInfoProcessor implements RequestProcessor {

    private NodeContainer storage;

    @Autowired
    public NodesInfoProcessor(NodeContainer storage) {
        this.storage = storage;
    }

    @Override
    public Response processRequest(Request request) {
        RequestBody data = request.getBody();
        String command = data.getParameter("command");
        if (command != null){
            switch (command){
                case "allNodes":
                    return getNodesInfo(false);
                case "activeNodes":
                    return getNodesInfo(true);
                case "nodeTypes":
                    return getNodeTypes();
                case "nodeLocations":
                    return getNodeLocations();
            }
        }

        return new Response(FAILURE);
    }

    private Response getNodesInfo(boolean isActiveOnly) {
        Response response = new Response();
        response.setStatus(SUCCESS);

        List<NodeInfo> nodes = storage.getAllNodes().stream().filter(node -> {
            if(isActiveOnly) {
                if(node.isActive())
                    return true;
                else
                    return false;
            }
            else {
                return true;
            }
        }).map(Node::getNodeInfo).collect(Collectors.toList());
        response.put("nodes", nodes);
        return response;
    }

    private Response getNodeTypes(){
        Response response = new Response();
        response.setStatus(SUCCESS);
        response.put("nodeTypes", storage.getAllNodes().stream().map(node -> node.getTypeName()).collect(Collectors.toSet()));
        return response;
    }

    private Response getNodeLocations(){
        Response response = new Response();
        response.setStatus(SUCCESS);
        response.put("nodeLocations", storage.getAllNodes().stream().map(node -> node.getNodeLocation()).collect(Collectors.toSet()));
        return response;
    }

    @Override
    public boolean isApplicable(Request request) {
        String type =  request.getBody().getParameter("type");
        return type != null && type.equals("general");
    }
}
