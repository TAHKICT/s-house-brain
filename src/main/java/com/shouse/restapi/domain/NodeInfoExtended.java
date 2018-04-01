package com.shouse.restapi.domain;

import com.shouse.restapi.service.node.NodeControlType;
import com.shouse.restapi.service.node.NodeLocation;
import com.shouse.restapi.service.node.NodeStatus;
import com.shouse.restapi.service.node.NodeType;

public class NodeInfoExtended extends NodeInfo {

    private String ipAddress;
    private String networkSSID;
    private NodeStatus nodeStatus;
    private String value;

    public NodeInfoExtended(int id, int nodeTypeId, int nodeLocationId, int nodeControlId, String description) {
        super(id, nodeTypeId, nodeLocationId, nodeControlId, description);
        this.value = "false";
        this.nodeStatus = NodeStatus.ACTIVE;
    }

    public NodeInfoMessage getNodeInfoMessage(){
        return new NodeInfoMessage(getId(),
                NodeType.getNodeTypeById(getNodeTypeId()).getDescription(),
                NodeLocation.getNodeCategoryById(getNodeLocationId()).getDescription(),
                new NodeInfoMessageControl(NodeControlType.getNodeControllTypeById(getNodeControlTypeId()).getDescription(),value),
                getDescription());
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
