package com.shouse.restapi.service.client;

import com.shouse.restapi.communicators.service.NodeInfoExtended;

import java.util.List;

public interface WebApplicationService {

    public List<String> getMenuSortTypes();

    public List<NodeInfoExtended> getNodes(ClientRequestGetNodes clientRequestGetNodes);

    public List<NodeInfoExtended> getActiveNodes();

    public List<NodeInfoExtended> getActiveNodes(ClientRequestGetNodes clientRequestGetNodes);

    public List<NodeInfoExtended> getAllNodes();

    public ResponseForWebClient handleRequestFromClient(NodeParamChangeEvent nodeParamChangeEvent);

    public void sendNodeChangeRequestToClient(int nodeId, String value);
}
