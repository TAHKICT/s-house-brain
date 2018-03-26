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
     * @return request result
     */
    @RequestMapping("/noderegistration")
    public String nodeRegistration(@RequestParam(value="id") String nodeId,
                                   @RequestParam(value="ip") String ipAddress) {
        return NodesService.registerNode(nodeId,ipAddress);
    }

    /**
     * Handle requests from node.
     * @param nodeId
     * @param value
     * @return request result
     */
    @RequestMapping("/nodehandle")
    public String nodeHandle(@RequestParam(value = "id") String nodeId,
                            @RequestParam(value = "value") String value){
        return NodesService.handleNode(nodeId,value);
    }
}
