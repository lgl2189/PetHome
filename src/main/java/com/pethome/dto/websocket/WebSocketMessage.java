package com.pethome.dto.websocket;

import java.util.HashMap;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 23 17:12
 */


public interface WebSocketMessage<T> {

    /**
     * 获取消息类型
     * @return 消息类型
     */
    Integer getType();

    /**
     * 获取时间戳
     * @return 消息时间戳
     */
    String getTimestamp();

    /**
     * 获取消息临时id
     * @return 消息临时id
     */
    String getTempId();

    /**
     * 获取消息数据
     * @return 消息数据
     */
    T getData();

    /**
     * 返回存储消息属性的HashMap
     * @return attributeMap
     */
    HashMap<String,Object> getAttributeMap();

    /**
     * 设置消息属性
     * @param name 属性名
     * @param value 属性值
     */
    void setAttribute(String name, Object value);

    /**
     * 获取消息属性
     * @param name 属性名
     * @return 属性值
     */
    Object getAttribute(String name);

    /**
     * 删除消息属性
     * @param name 属性名
     */
    void removeAttribute(String name);

}