package com.shouse.restapi.controllers;

import com.shouse.restapi.domain.NodeInfoMessage;
import com.shouse.restapi.service.client.ClientRequestGetNodes;
import com.shouse.restapi.service.client.WebApplicationService;
import com.shouse.restapi.service.node.NodeType;
import com.shouse.restapi.service.client.RequestFromClientNodeParamChange;
import com.shouse.restapi.service.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/core-rest-api/for-web-application")
public class WebApplicationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WebApplicationService webApplicationService;

    /**
     * Returns sorting variants for menu.
     * @return
     */
    @RequestMapping("/menu/get-sort-types")
    public List<String> getMenuSortTypes(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        return webApplicationService.getMenuSortTypes();
    }

    /**
     *  Returns names of menu items by sorting type.
     * @param sortType
     * @param response
     * @return
     */
    @RequestMapping("/menu/get-items")
    public List<String> getNamesOfMenuItems(@RequestParam(value = "sortedBy") String sortType, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        return webApplicationService.getNamesOfMenuItems(sortType);
    }

    /**
     *  Returns list of nodes by criterias.
     * @param clientRequestGetNodes
     * @param response
     * @return
     */
    @RequestMapping("/content/get-nodes")
    public List<NodeInfoMessage> getNodes(@RequestBody ClientRequestGetNodes clientRequestGetNodes,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return webApplicationService.getActiveNodes(clientRequestGetNodes).stream().map(nodeInfoExtended -> nodeInfoExtended.getNodeInfoMessage()).collect(Collectors.toList());
    }

    /**
     *  Handle request from web client about changing node params.
     * @param requestFromClientNodeParamChange
     * @return
     */
    @RequestMapping("/content/node-parameter-change")
    public ClientResponse handleNodeParameterChange(@RequestBody RequestFromClientNodeParamChange requestFromClientNodeParamChange) {
        log.info("WebApplicationController. handleRequestFromWebApplication. :" + requestFromClientNodeParamChange);
        return webApplicationService.handleRequestFromClient(requestFromClientNodeParamChange);
    }

    @RequestMapping("/get-active-nodes/{nodeTypeDescription}")
    public List<NodeInfoMessage> getActiveNodesByType(
            @PathVariable(value="nodeTypeDescription") String nodeTypeDescription,
            HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.info("WebApplicationController. getActiveNodesByType. nodeTypeDescription:" + nodeTypeDescription);
        return webApplicationService.getActiveNodes(NodeType.getNodeTypeByDescription(nodeTypeDescription)).stream().map(nodeInfoExtended -> nodeInfoExtended.getNodeInfoMessage()).collect(Collectors.toList());
    }
}
