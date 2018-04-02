package com.shouse.restapi.controllers;

import com.shouse.restapi.service.user.UserRequest;
import com.shouse.restapi.service.user.UserResponse;
import com.shouse.restapi.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UsersWebSocketController {

    @Autowired
    UsersService service;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/to-server")
    @SendTo("/to-user/messages")
    public UserResponse webSocketMessages(UserRequest userRequest, HttpServletResponse response) throws Exception {
//        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.sleep(1000); // simulated delay
        return service.handleUserRequest(userRequest);
    }


    public void fireResponse(UserResponse userResponse) {
        this.template.convertAndSend("/to-user/messages", userResponse);
    }

    @PostConstruct
    public void init() {
        service.setUsersWebSocketController(this);
    }

}
