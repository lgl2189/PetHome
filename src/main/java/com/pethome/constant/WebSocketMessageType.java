package com.pethome.constant;

/**
 * @author ：Star
 * @description ：WebSocket消息类型枚举
 * 与前端保持一致，用于标识不同类型的消息
 * @date ：2025 6月 23 17:06
 */
public enum WebSocketMessageType {
    // 认证相关 (100-199)
    AUTH_REQUEST(100, "认证请求", MessageDirection.CLIENT_TO_SERVER),
    AUTH_RESPONSE(101, "认证响应", MessageDirection.SERVER_TO_CLIENT),

    // 消息相关 (200-299)
    PRIVATE_MESSAGE(200, "单聊消息", MessageDirection.CLIENT_TO_SERVER),
    GROUP_MESSAGE(201, "群聊消息", MessageDirection.CLIENT_TO_SERVER),
    MESSAGE_BROADCAST(202, "消息广播", MessageDirection.SERVER_TO_CLIENT),
    MESSAGE_ACK(203, "消息确认", MessageDirection.SERVER_TO_CLIENT),
    MESSAGE_RECALL(204, "消息撤回", MessageDirection.CLIENT_TO_SERVER),
    RECALL_NOTIFICATION(205, "撤回通知", MessageDirection.SERVER_TO_CLIENT),

    // 用户状态相关 (300-399)
    USER_ONLINE(300, "用户上线通知", MessageDirection.SERVER_TO_CLIENT),
    USER_OFFLINE(301, "用户下线通知", MessageDirection.SERVER_TO_CLIENT),
    TYPING_INDICATOR(302, "正在输入提示", MessageDirection.CLIENT_TO_SERVER),
    TYPING_BROADCAST(303, "输入状态广播", MessageDirection.SERVER_TO_CLIENT),

    // 消息状态相关 (400-499)
    READ_RECEIPT(400, "已读回执", MessageDirection.CLIENT_TO_SERVER),
    READ_NOTIFICATION(401, "已读通知", MessageDirection.SERVER_TO_CLIENT),

    // 系统通知相关 (500-599)
    SYSTEM_NOTICE(500, "系统通知", MessageDirection.SERVER_TO_CLIENT),
    ERROR_NOTIFICATION(501, "错误通知", MessageDirection.BIDIRECTIONAL),

    // 连接管理相关 (600-699)
    HEARTBEAT_REQUEST(600, "心跳请求", MessageDirection.BIDIRECTIONAL),
    HEARTBEAT_RESPONSE(601, "心跳响应", MessageDirection.BIDIRECTIONAL),

    // 历史数据相关 (700-799)
    HISTORY_REQUEST(700, "历史消息请求", MessageDirection.CLIENT_TO_SERVER),
    HISTORY_RESPONSE(701, "历史消息响应", MessageDirection.SERVER_TO_CLIENT),

    // 用户列表相关 (800-899)
    ONLINE_USERS_REQUEST(800, "在线用户列表请求", MessageDirection.CLIENT_TO_SERVER),
    ONLINE_USERS_RESPONSE(801, "在线用户列表响应", MessageDirection.SERVER_TO_CLIENT);

    public final int code;
    public final String description;
    public final MessageDirection direction;

    WebSocketMessageType(int code, String description, MessageDirection direction) {
        this.code = code;
        this.description = description;
        this.direction = direction;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public MessageDirection getDirection() {
        return direction;
    }

    /**
     * 根据代码获取消息类型枚举
     *
     * @param code 消息类型代码
     * @return 对应的消息类型枚举，若未找到则返回null
     */
    public static WebSocketMessageType fromCode(int code) {
        for (WebSocketMessageType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}

/**
 * 消息方向枚举
 */
enum MessageDirection {
    CLIENT_TO_SERVER("client_to_server", "客户端→服务器"),
    SERVER_TO_CLIENT("server_to_client", "服务器→客户端"),
    BIDIRECTIONAL("bidirectional", "双向");

    private final String value;
    private final String description;

    MessageDirection(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
