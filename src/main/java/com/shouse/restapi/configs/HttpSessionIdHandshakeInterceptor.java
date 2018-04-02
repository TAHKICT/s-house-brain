package com.shouse.restapi.configs;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class HttpSessionIdHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpResponse instanceof ServletServerHttpResponse) {
            ServletServerHttpResponse response = (ServletServerHttpResponse) serverHttpResponse;
            response.getHeaders().add("Access-Control-Allow-Origin", "*");
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        if (serverHttpResponse instanceof ServletServerHttpResponse) {
            ServletServerHttpResponse response = (ServletServerHttpResponse) serverHttpResponse;
            response.getHeaders().add("Access-Control-Allow-Origin", "*");
        }
    }
}
