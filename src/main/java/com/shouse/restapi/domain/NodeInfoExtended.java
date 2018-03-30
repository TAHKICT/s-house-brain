package com.shouse.restapi.domain;

import com.shouse.restapi.service.node.NodeStatus;

public class NodeInfoExtended extends NodeInfo {

    private String ipAddress;
    private String networkSSID;
    private NodeStatus nodeStatus;
    private String value;

    public NodeInfoExtended(int id, int nodeTypeId, String description) {
        super(id, nodeTypeId, description);
    }

    public NodeInfoExtended(int id, int nodeTypeId, String ipAddress, String networkSSID, NodeStatus nodeStatus, String value) {
        super(id, nodeTypeId);
        this.ipAddress = ipAddress;
        this.networkSSID = networkSSID;
        this.nodeStatus = nodeStatus;
        this.value = value;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getNetworkSSID() {
        return networkSSID;
    }

    public void setNetworkSSID(String networkSSID) {
        this.networkSSID = networkSSID;
    }

    public NodeStatus getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(NodeStatus nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
