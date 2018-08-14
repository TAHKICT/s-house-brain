package com.shouse.restapi.communicators.service;

import com.shouse.restapi.service.node.*;
import shouse.core.node.NodeInfo;
import shouse.core.node.NodeLocation;

public class NodeInfoExtended {

    private int nodeId;
    private String ipAddress;
    private String networkSSID;
    private NodeStatus nodeStatus;
    private String value;

    public NodeInfoExtended(int nodeId, String ipAddress, NodeStatus nodeStatus) {
        this.nodeId = nodeId;
        this.ipAddress = ipAddress;
        this.nodeStatus = nodeStatus;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
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

    @Override
    public String toString() {
        return "NodeInfoExtended{" +
                "ipAddress='" + ipAddress + '\'' +
                ", networkSSID='" + networkSSID + '\'' +
                ", nodeStatus=" + nodeStatus +
                ", value='" + value + '\'' +
                '}';
    }
}
