package com.shouse.restapi.service.client;

import com.shouse.restapi.domain.NodeInfoExtended;
import com.shouse.restapi.service.node.NodeType;

import java.util.List;

public interface WebApplicationService {

    public List<String> getMenuSortTypes();

    public List<String> getNamesOfMenuItems(String sortType);

    public List<NodeInfoExtended> getNodes(ClientRequestGetNodes clientRequestGetNodes);

    public List<NodeInfoExtended> getActiveNodes();

    public List<NodeInfoExtended> getActiveNodes(ClientRequestGetNodes clientRequestGetNodes);

    public List<NodeInfoExtended> getActiveNodes(NodeType nodeType);

    public List<NodeInfoExtended> getAllNodes();

    public ClientResponse handleRequestFromClient(RequestFromClientNodeParamChange requestFromClientNodeParamChange);

    public void sendNodeChangeRequestToClient(int nodeId, String value);
}
