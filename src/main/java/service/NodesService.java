package service;

import dao.NodesDao;
import domain.NodeInfo;

import java.text.ParseException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class holds logical part.
 */
public class NodesService {

    private static Map<Long, NodeInfo> nodeInfoMap;
    private static boolean isSynchronized = false;

    /**
     * Only nodes, which exists in storage can be registered in system as active. The rest will bow out.
     * @param nodeId
     * @param ipAddress
     * @return the result message
     */
    public static String registerNode(String nodeId, String ipAddress){
        if(nodeId(nodeId) == null)
            return Messages.nodeIdFormatIsNotValid;

            Long id = nodeId(nodeId);
            if(nodeInfoMap.containsKey(id)) {

                if(!validIP(ipAddress))
                    return String.format(Messages.nodeIPAddressIsNotValid, ipAddress);

                nodeInfoMap.get(id).setIpAddress(ipAddress);
                nodeInfoMap.get(id).setStatusId(NodeStatus.ACTIVE.getStatusCode());
                return Messages.nodeRegistered;
            } else
                return Messages.nodeNotFound;
    }

    /**
     * When system starts, nodes map should be token from the storage.
     */
    public static void nodeInfoMapSynchronization(){
        nodeInfoMap = NodesDao.getNodes().stream().collect(
                Collectors.toMap(NodeInfo::getId, NodeInfo -> NodeInfo)
        );

        isSynchronized = true;
    }

    /**
     * Handle requests from nodes.
     * @param nodeId
     * @param value
     * @return
     */
    public static String handleNode(String nodeId, String value) {
        if (!nodeInfoMap.containsKey(nodeId))
            return Messages.nodeNotFound;
        else if(nodeInfoMap.get(nodeId).getStatusId() == NodeStatus.SWITCHED_OFF.getStatusCode())
            return Messages.nodeIsNotActive;

        nodeInfoMap.get(nodeId).setValue(value);

        return Messages.nodeHandledSuccessfully;
    }

    private static Long nodeId(String nodeIdString){
        try {
            return Long.parseLong(nodeIdString);
        }catch (NumberFormatException ex){
            return null;
        }
    }

    private static boolean validIP (String ip) {
        try {
            if ( ip == null || ip.isEmpty() ) {
                return false;
            }

            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
                return false;
            }

            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            if ( ip.endsWith(".") ) {
                return false;
            }

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
