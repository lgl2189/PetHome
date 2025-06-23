package com.pethome.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.pethome.config.web.UserChatWebSocketConfig;
import com.pethome.constant.WebSocketMessageType;
import com.pethome.dto.websocket.ObjectWebSocketMessage;
import com.pethome.dto.websocket.StringWebSocketMessage;
import com.pethome.entity.mybatis.MessageRecord;
import com.pethome.service.MessageRecordService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 21 14:27
 */
@ServerEndpoint(value = "/ws/chat", configurator = UserChatWebSocketConfig.class)
@CrossOrigin(origins = "*")
@Component
public class UserChatEndPoint extends HeartBeatEndPoint implements ApplicationContextAware {
    private final static Map<Integer, Map<String, Session>> USER_SESSION_MAP = new ConcurrentHashMap<>();

    private static final ObjectMapper objectMapper = new ObjectMapper();
    static{
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        objectMapper.registerModule(javaTimeModule);
        // 处理旧版 Date 类型（可选）
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    private static MessageRecordService messageRecordService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        messageRecordService = applicationContext.getBean(MessageRecordService.class);
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        Boolean isRejected = (Boolean) session.getUserProperties().get("isRejected");
        if (isRejected != null && isRejected) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "Token解析失败"));
            return;
        }
        String token = (String) session.getUserProperties().get("token");
        Integer userId = (Integer) session.getUserProperties().get("userId");
        USER_SESSION_MAP.computeIfAbsent(userId, map -> new ConcurrentHashMap<>()).put(token, session);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        ObjectWebSocketMessage messageObject = objectMapper.readValue(message, ObjectWebSocketMessage.class);
        WebSocketMessageType messageType = WebSocketMessageType.fromCode(messageObject.getType());
        if (messageType == null) {
            return;
        }
        switch (messageType) {
            case PRIVATE_MESSAGE:
                onPrivateMessage(session, messageObject);
        }
    }

    @OnClose
    public void onClose(Session session) {
        String token = (String) session.getUserProperties().get("token");
        if (token == null) {
            return;
        }
        Integer userId = (Integer) session.getUserProperties().get("userId");
        USER_SESSION_MAP.computeIfPresent(userId, (key, value) -> {
            value.remove(token);
            return value;
        });
    }

    public void onPrivateMessage(Session session, ObjectWebSocketMessage message) throws IOException {
        MessageRecord messageRecord = objectMapper.readValue(message.getData().toString(), MessageRecord.class);
        boolean isSaved = messageRecordService.save(messageRecord);
        if (!isSaved) {
            return;
        }
        // 发送收到消息确认消息
        StringWebSocketMessage responseMessage = new StringWebSocketMessage();
        responseMessage.setType(WebSocketMessageType.MESSAGE_ACK.getCode());
        responseMessage.setTimestamp(String.valueOf(Instant.now().toEpochMilli()));
        responseMessage.setTempId(message.getTempId());
        responseMessage.setData(objectMapper.writeValueAsString(messageRecord));
        session.getBasicRemote().sendText(objectMapper.writeValueAsString(responseMessage));
        // 广播消息
        StringWebSocketMessage broadcastMessage = new StringWebSocketMessage();
        broadcastMessage.setType(WebSocketMessageType.PRIVATE_MESSAGE.getCode());
        broadcastMessage.setTimestamp(String.valueOf(Instant.now().toEpochMilli()));
        broadcastMessage.setTempId(message.getTempId());
        broadcastMessage.setData(objectMapper.writeValueAsString(messageRecord));
        String broadcastJson = objectMapper.writeValueAsString(broadcastMessage);
        Map<String, Session> userIdMap = USER_SESSION_MAP.computeIfPresent(messageRecord.getReceiverId(), (key, value) -> value);
        if (userIdMap != null) {
            for (Map.Entry<String, Session> entry : userIdMap.entrySet()) {
                Session targetSession = entry.getValue();
                targetSession.getBasicRemote().sendText(broadcastJson);
            }
        }
    }
}