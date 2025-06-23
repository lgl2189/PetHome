package com.pethome.dto.websocket;

import java.util.HashMap;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 23 17:26
 */


public class StringWebSocketMessage implements WebSocketMessage<String> {

    private Integer type;
    private String timestamp;
    private String tempId;
    private String data;
    private final HashMap<String, Object> attributeMap = new HashMap<>();

    public StringWebSocketMessage() {
    }

    public StringWebSocketMessage(Integer type, String timestamp, String tempId, String data) {
        this.type = type;
        this.timestamp = timestamp;
        this.tempId = tempId;
        this.data = data;
    }

    @Override
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    @Override
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public HashMap<String, Object> getAttributeMap() {
        return attributeMap;
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributeMap.put(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return attributeMap.get(name);
    }

    @Override
    public void removeAttribute(String name) {
        attributeMap.remove(name);
    }
}