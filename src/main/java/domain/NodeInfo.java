package domain;

public class NodeInfo {

    private Long id;
    private String ipAddress;
    private String networkSSID;
    private int status;

    public NodeInfo() {
    }

    public NodeInfo(Long id, String ipAddress, String networkSSID, int status) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.networkSSID = networkSSID;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
