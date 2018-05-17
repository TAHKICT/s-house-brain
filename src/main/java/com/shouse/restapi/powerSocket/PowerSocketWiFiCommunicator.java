package com.shouse.restapi.powerSocket;

import shouse.core.Common.NodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shouse.core.Common.SystemConstants;
import shouse.core.communication.Communicator;
import shouse.core.communication.Packet;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("/core-rest-api/wifi-communicator/power-socket")
public class PowerSocketWiFiCommunicator implements Communicator{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate;
    private Packet packet;
    private boolean hasPacket;
    private Map nodesIP = new HashMap<Integer,String >();

    @Autowired
    public PowerSocketWiFiCommunicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private MultiValueMap<String, String> getDataFromPacket(Packet packet) {
        return new LinkedMultiValueMap<>(packet.getData().entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> {
                    List<String> vals = new ArrayList<>();
                    vals.add(entry.getValue());
                    return vals;
                })));
    }

    @RequestMapping("")
    public String receiveRequestFromWiFiPowerSocketNode(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "requestId") String requestId,
            @RequestParam(value = "nodeTaskStatus") String nodeTaskStatus,
            @RequestParam(value = "switched") String switched,
            HttpServletRequest request) {
        log.info("receiveRequestFromWiFiPowerSocketNode");

        nodesIP.put(id, request.getRemoteAddr());

        packet = new Packet(id);
        packet.putData("nodeTypeId", String.valueOf(NodeType.POWER_SOCKET.getId()));
        packet.putData(SystemConstants.requestId, requestId);
        packet.putData(SystemConstants.nodeTaskStatus, nodeTaskStatus);
        packet.putData("switched", switched);
        hasPacket = true;
        return "got it";
    }

    @Override
    public void sendPacket(Packet packet) {
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl("http://" + nodesIP.get(packet.getNodeId()) + "/command-from-server")
                .queryParams(getDataFromPacket(packet));
        log.info("sendRequest. url: " + url.toUriString());
        restTemplate.getForEntity(url.toUriString(), String.class);
    }

    @Override
    public Packet receivePacket() {
        hasPacket = false;
        return packet;
    }

    @Override
    public boolean hasNewPacket() {
        return hasPacket;
    }

}
