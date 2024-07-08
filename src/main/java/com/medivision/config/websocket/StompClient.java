package com.medivision.config.websocket;

import com.medivision.medivision.chat.chat.domain.Chat;
import com.medivision.medivision.chat.chat.dto.ChatRequestDto;
import com.medivision.medivision.chat.chatroomAndMember.domain.ChatroomAndMember;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.*;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class StompClient {
    private static int chatroomId = -1;
    private static StompClient client = new StompClient();
    private static Set<Integer> chatroomIdSet = new HashSet<>();

    private StompClient() { }

    public static StompClient getInstance() {
        return client;
    }

    public static boolean isChatroomIdInSet(int chatroomId) {
        return chatroomIdSet.contains(chatroomId);
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }

    @Async
    public CompletableFuture<Void> connectUserChatroomAll(List<ChatroomAndMember> chatroomAndMembers) {
        for (ChatroomAndMember chatroomAndMember : chatroomAndMembers) {
            this.chatroomId = chatroomAndMember.getRoomId();

            if (!chatroomIdSet.contains(this.chatroomId)) {
                chatroomIdSet.add(this.chatroomId);
                connect();
            }
        }

        return CompletableFuture.completedFuture(null);
    }

    public void connect() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());

        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.afterPropertiesSet();
        stompClient.setTaskScheduler(taskScheduler);

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        InetAddress SERVER_IP = null;
        int PORT = 8080;

        try {
            SERVER_IP = InetAddress.getLocalHost();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String CONNECTION_URL = "ws://" +  SERVER_IP.getHostAddress() + ":" + PORT + "/ws";
        //String CONNECTION_URL = "ws://localhost:" + PORT + "/ws";

        System.out.println(CONNECTION_URL);

        stompClient.connect(CONNECTION_URL, sessionHandler);
    }

    private class MyStompSessionHandler extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            String SUBSCRIBE_URL = "/topic/chatroom/" + chatroomId;
            session.subscribe(SUBSCRIBE_URL, this);

            System.out.println("Subscribed to: " + SUBSCRIBE_URL);
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            System.out.println("Stomp Client Received: " + ((ChatRequestDto) payload).getMessage());
        }

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return ChatRequestDto.class;
        }

        @Override
        public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
            System.err.println("Error: " + exception.getMessage());
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            System.err.println("Transport Error: " + exception.getMessage());
        }
    }
}
