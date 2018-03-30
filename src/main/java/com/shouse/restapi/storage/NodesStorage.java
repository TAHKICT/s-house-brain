package com.shouse.restapi.storage;

import com.shouse.restapi.domain.NodeInfo;

import java.util.List;

public interface NodesStorage {

    List<NodeInfo> getNodes();
}
