package com.shouse.restapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shouse.restapi.service.NodesService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class NodesController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NodesService service;

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
                                   @RequestParam(value="ip") String ipAddress,
                                   HttpServletRequest request) {

        String result = service.registerNode(nodeId,ipAddress);
        log.info("Node registration. NodeId: {}, ip address: {}. Result: {}", nodeId, ipAddress, result);
        return result;
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

        String result = service.handleNode(nodeId,value);
        log.info("Handle request from node. NodeId: {}, value: {}. Result: {}", nodeId, result);
        return result;
    }
}
