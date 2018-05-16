package com.shouse.restapi.service.client;

import com.shouse.restapi.communicator.service.NodeInfoExtended;
import shouse.core.Common.NodeType;

import java.util.List;

public interface WebApplicationService {

    public List<String> getMenuSortTypes();

    public List<String> getNamesOfMenuItems(String sortType);

    public List<NodeInfoExtended> getNodes(ClientRequestGetNodes clientRequestGetNodes);

    public List<NodeInfoExtended> getActiveNodes();

    public List<NodeInfoExtended> getActiveNodes(ClientRequestGetNodes clientRequestGetNodes);

    public List<NodeInfoExtended> getActiveNodes(NodeType nodeType);

    public List<NodeInfoExtended> getAllNodes();

    public ResponseForWebClient handleRequestFromClient(NodeParamChangeEvent nodeParamChangeEvent);

    public void sendNodeChangeRequestToClient(int nodeId, String value);
}
