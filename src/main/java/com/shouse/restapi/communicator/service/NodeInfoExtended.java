package com.shouse.restapi.communicator.service;

import com.shouse.restapi.service.node.*;
import shouse.core.node.NodeInfo;

public class NodeInfoExtended extends NodeInfo {

    private String ipAddress;
    private String networkSSID;
    private NodeStatus nodeStatus;
    private String value;

    public NodeInfoExtended(int id, int nodeTypeId, int nodeLocationId, String description) {
        super(id, nodeTypeId, nodeLocationId, description);
    }

    public NodeInfoExtended(NodeInfo nodeInfo, String ipAddress, String networkSSID, NodeStatus nodeStatus, String value){
        super(nodeInfo.getId(), nodeInfo.getNodeTypeId(), nodeInfo.getNodeLocationId(), nodeInfo.getDescription());
        this.ipAddress = ipAddress;
        this.networkSSID = networkSSID;
        this.nodeStatus = nodeStatus;
        this.value = value;
    }

//    public NodeInfoMessage getNodeInfoMessage(){
//        return new NodeInfoMessage(getId(),
//                NodeType.getNodeTypeById(getNodeTypeId()).getName(),
//                NodeLocation.getNodeLocationById(getNodeLocationId()).getName(),
//                new NodeInfoMessageControl("checkbox", value),
//                getDescription());
//    }

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
