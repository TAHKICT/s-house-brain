package com.shouse.restapi.storage;

import com.shouse.restapi.domain.NodeInfoExtended;

import java.util.List;

public interface NodesStorage {

    List<NodeInfoExtended> getNodes();
}
