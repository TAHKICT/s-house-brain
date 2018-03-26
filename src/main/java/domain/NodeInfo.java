package domain;

public class NodeInfo {

    private Long id;
    private String ipAddress;
    private String networkSSID;
    private int statusId;
    private int nodeTypeId;
    private String value;

    public NodeInfo() {
    }

    public NodeInfo(Long id, String ipAddress, String networkSSID, int statusId) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.networkSSID = networkSSID;
        this.statusId = statusId;
    }

    public NodeInfo(Long id, String ipAddress, String networkSSID, int statusId, int nodeTypeId, String value) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.networkSSID = networkSSID;
        this.statusId = statusId;
        this.nodeTypeId = nodeTypeId;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(int nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
