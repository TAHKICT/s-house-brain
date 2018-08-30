//package com.shouse.restapi.dummy.dummyNode;
//
//import shouse.core.communication.NodeCommunicator;
//import shouse.core.communication.Packet;
//import shouse.core.node.Node;
//import shouse.core.node.NodeInfo;
//import shouse.core.node.NodeLocation;
//import shouse.core.node.request.Request;
//import shouse.core.node.response.Response;
//
///**
// * Created by Maks on 10.05.2018.
// */
//public class TestNode extends Node {
//
//    private NodeCommunicator communicator;
//    private NodeLocation nodeLocationId;
//    private String description;
//
//    public TestNode(int id, NodeLocation nodeLocationId, String description, NodeCommunicator communicator) {
//        super(id, NodeType.LIGHT.getId(), false);
//        this.communicator = communicator;
//        this.nodeLocationId = nodeLocationId;
//        this.description = description;
//    }
//
//    @Override
//    public NodeInfo getNodeInfo() {
//        return new NodeInfo(getId(), getTypeName(), nodeLocationId, description);
//    }
//
//    @Override
//    public Response processRequest(Request request) {
//        if (request.getBody().getParameter("aaaa") != null){
//            communicator.sendPacket(createDeletePacket(request));
//            return new Response();
//        }
//
//        throw new RuntimeException();
//    }
//
//    private Packet createDeletePacket(Request request) {
//        return new Packet(getId());
//    }
//
//    @Override
//    public void processPacket(Packet packet) {
//
//    }
//}
