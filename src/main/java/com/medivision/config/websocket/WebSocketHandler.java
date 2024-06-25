package com.medivision.config.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Service
//@ServerEndpoint(value="/chat")
public class WebSocketHandler extends TextWebSocketHandler {
    //private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override // 웹 소켓 연결시
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connection established");

        sessions.put(session.getId(), session);

    }

    @Override // 메시지 전달
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());
    }

    @Override // 웹소켓 통신 에러시
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("websocket transport error");
        super.handleTransportError(session, exception);
    }

    @Override // 웹 소켓 연결 종료시
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("websocket connection closed");
        super.afterConnectionClosed(session, status);
    }
}