package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.NodesService;

@RestController
public class NodesController {

    /**
     * Method gets information about new incoming node. Every node has it's description in the storage.
     * If node id is not recognized - node doesn't connect to the smart system.
     * If node id found in the storage - node status change to 'Active' and node ip will be updated.
     * @param ipAddress ip address
     * @param nodeId unique identifier
     */
    @RequestMapping("/noderegistration")
    public String greeting(@RequestParam(value="ip") String ipAddress,
                         @RequestParam(value="id") String nodeId) {
        return NodesService.registerNode(nodeId,ipAddress);
    }
}
