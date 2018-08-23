package com.shouse.brain.communication;

import com.shouse.brain.api.entities.NodeInfoExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shouse.brain.service.Messages;
import com.shouse.brain.service.node.NodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shouse.core.communication.NodeCommunicator;
import shouse.core.communication.Packet;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.stream.Collectors.toMap;

/**
 * Example of alive request from node: http://localhost:8181/core-rest-api/communicator?id=1&nodeTypeName=PowerSocketNode
 */
@RestController
@RequestMapping("/core-rest-api/communicator")
public class HttpNodCommunicator implements NodeCommunicator {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate;
    private Map<Integer, NodeInfoExtended> nodeInfoMap;

    private ConcurrentLinkedQueue<Packet> packets = new ConcurrentLinkedQueue<>();

    @Autowired
    public HttpNodCommunicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.nodeInfoMap = new LinkedHashMap<>();
    }

    @RequestMapping("")
    public String handleNode(@RequestParam Map<String, String> params,
                           HttpServletRequest request) {

        LOGGER.info("method: ".concat(Thread.currentThread().getStackTrace()[1].getMethodName()));

        if (!request.getRemoteAddr().contains("0:0:0:0")) {
            handleAliveRequestFromNode(params.get("id"), request.getRemoteAddr());
        }else {
            LOGGER.info("Received request from fake node (developer mode probably).");
        }

        Packet packet = new Packet(Integer.parseInt(params.get("id")));
        packet.setData(params);
        packets.add(packet);
        return "got it";
    }

    private String handleAliveRequestFromNode(String nodeId, String ipAddress) {
        if(nodeId == null)
            return Messages.nodeIdFormatIsNull;

        if(!validNodeId(nodeId))
            return Messages.nodeIdFormatIsNotValid;

        if(!validIP(ipAddress))
            return String.format(Messages.nodeIPAddressIsNotValid, ipAddress);

        int id = Integer.valueOf(nodeId);

        nodeInfoMap.put(id, createNodeInfo(ipAddress, id));
        return Messages.nodeRegistered;

    }

    private NodeInfoExtended createNodeInfo(String ipAddress, int id) {
        return new NodeInfoExtended(id, ipAddress, NodeStatus.ACTIVE);
    }

    private static boolean validNodeId(String nodeId){
        try {
            Integer.valueOf(nodeId);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
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

    @Override
    public void sendPacket(Packet packet) {
        String ip = getNodeIp(packet.getNodeId());

        if (ip.equals("")){
            LOGGER.warn("Node id was not found. Packet will not send.");
            return;
        }

        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl("http://" + ip + "/command-from-server")
                .queryParams(getDataFromPacket(packet));

        LOGGER.info("sendRequest. url: " + url.toUriString());
        restTemplate.getForEntity(url.toUriString(), String.class);
    }

    private MultiValueMap<String, String> getDataFromPacket(Packet packet) {
        return new LinkedMultiValueMap<>(packet.getData().entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> {
                    List<String> vals = new ArrayList<>();
                    vals.add(entry.getValue());
                    return vals;
                })));
    }

    private String getNodeIp(int nodeId) {
        try {
            NodeInfoExtended nodeInfo = nodeInfoMap.get(nodeId);
            return nodeInfo.getIpAddress();
        }catch (NullPointerException ex){
            return "";
        }
    }

    @Override
    public Packet receivePacket() {
        return packets.poll();
    }

    @Override
    public boolean hasNewPacket() {
        //because isEmpty() is not constant time operation, should be refactored somehow
        return !packets.isEmpty();
    }
}
