package com.pethome.endpoint;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 21 14:27
 */
@ServerEndpoint("/chat")
@Component
public class UserChatEndPoint {
    @OnOpen
    public void onOpen(Session session) {

    }

    @OnMessage
    public void onMessage(Session session, String message) {

    }

    @OnClose
    public void onClose(Session session) {

    }
}