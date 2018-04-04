package com.shouse.restapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shouse.restapi.service.node.NodesService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/core-rest-api/for-nodes")
public class NodesController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NodesService service;

    /**
     * Handle requests from node.
     * @param nodeId
     * @param value
     * @return request result
     */
    @RequestMapping("/request-from-node")
    public String handleRequestFromNode(@RequestParam(value = "id") String nodeId,
                                        @RequestParam(value = "value") String value){

        String result = service.handleNode(nodeId,value);
        log.info("Handle request from node. NodeId: {}, value: {}. Result: {}", nodeId, result);
        return result;
    }

    /**
     * Method gets alive message from node. Every node has it's description in the storage.
     * If node ID is not recognized - node will not connect to the smart system.
     * If node ID found in the storage - node status change to 'Active' and node ip will be updated.
     * @param nodeId unique identifier
     * @return request result
     */
    @RequestMapping("/alive-request-from-node")
    public String handleAliveRequestFromNode(@RequestParam(value = "id") String nodeId){
        return service.handleAliveRequestFromNode(nodeId,"0.0.0.0");
    }
}
