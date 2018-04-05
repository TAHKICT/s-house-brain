package com.shouse.restapi.service.client;

import com.shouse.restapi.domain.NodeInfoExtended;
import com.shouse.restapi.service.node.NodeType;

import java.util.List;

public interface ClientsService {

    public List<NodeInfoExtended> getActiveNodes();

    public List<NodeInfoExtended> getActiveNodes(NodeType nodeType);

    public List<NodeInfoExtended> getAllNodes();

    public ClientResponse handleRequestFromClient(ClientRequest clientRequest);

    public void sendNodeChangeRequestToClient(int nodeId, String value);
}
