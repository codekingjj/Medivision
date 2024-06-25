package com.medivision.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/chat")     // 웹소켓 연결 엔드포인트
                .setAllowedOriginPatterns("*")  // CORS error
                .withSockJS();                  // SockJS로 웹소켓 연결
    }

    @Bean public org.springframework.web.socket.WebSocketHandler webSocketHandler() {
        return new WebSocketHandler();
    }
}