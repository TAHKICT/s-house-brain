package com.shouse.restapi.api.controllers;

import com.shouse.restapi.domain.NodeInfoMessage;
import com.shouse.restapi.service.client.ClientRequestGetNodes;
import com.shouse.restapi.service.client.NodeParamChangeEvent;
import com.shouse.restapi.service.client.WebApplicationService;
import com.shouse.restapi.service.node.NodeType;
import com.shouse.restapi.service.client.ResponseForWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import shouse.core.api.Notifier;
import shouse.core.api.RequestDispatcher;
import shouse.core.node.request.Request;
import shouse.core.node.response.Message;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/core-rest-api/for-web-application")
public class WebApplicationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private WebApplicationService webApplicationService;

    private RequestDispatcher requestDispatcher;

    public WebApplicationController(WebApplicationService webApplicationService, RequestDispatcher requestDispatcher) {
        this.webApplicationService = webApplicationService;
        this.requestDispatcher = requestDispatcher;
    }

    @RequestMapping(value = "/endpoint", method = RequestMethod.POST)
    public Message apiEntryPoint(@RequestBody WebRequest webRequest){
        Request request = createRequest(webRequest);
        request.setNotifier(defineNotifier());
        return requestDispatcher.dispatchRequest(request);
    }

    //TODO: get notifier
    private Notifier defineNotifier() {
        return (message) -> {};
    }

    private Request createRequest(WebRequest requestFromClient) {
        Request request = new Request();
        request.setNodeId(requestFromClient.getNodeId());
        request.setBody(requestFromClient.getBody());
        return request;
    }

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
     * @param nodeParamChangeEvent
     * @return
     */
    @RequestMapping("/content/node-parameter-change")
    public ResponseForWebClient handleNodeParameterChange(@RequestBody NodeParamChangeEvent nodeParamChangeEvent) {
        log.info("WebApplicationController. handleRequestFromWebApplication. :" + nodeParamChangeEvent);
        return webApplicationService.handleRequestFromClient(nodeParamChangeEvent);
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
