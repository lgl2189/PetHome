package com.pethome.config.web;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.constant.Constant;
import com.pethome.dto.UserDetail;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 21 14:21
 */

@Configuration
public class UserChatWebSocketConfig extends ServerEndpointConfig.Configurator implements ApplicationContextAware {

    private static RedisTemplate<String, Object> redisTemplate;
    private static ObjectMapper objectMapper;

    public UserChatWebSocketConfig() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = applicationContext.getBean("redisTemplate", RedisTemplate.class);
        objectMapper = applicationContext.getBean(ObjectMapper.class);
        Assert.notNull(redisTemplate, "redisTemplate must not be null");
        Assert.notNull(objectMapper, "objectMapper must not be null");
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        // 从查询参数获取 token
        Map<String, List<String>> params = request.getParameterMap();
        List<String> tokens = params.get("token");

        if (tokens != null && !tokens.isEmpty()) {
            String token = tokens.get(0);
            try {
                if (validateToken(token)) {
                    // 将用户Token和UserId存入 WebSocket 会话属性
                    config.getUserProperties().put("token", token);
                    String userJson = JWT.of(token).getPayloads().get("user", String.class);
                    UserDetail user = objectMapper.readValue(userJson, UserDetail.class);
                    config.getUserProperties().put("userId", user.getUserId());
                }
                else {
                    config.getUserProperties().put("isRejected", true);
                }
            }
            catch (JsonProcessingException e) {
                config.getUserProperties().put("isRejected", true);
            }
        }
        else{
            config.getUserProperties().put("isRejected", true);
        }
    }

    private boolean validateToken(String token) throws JsonProcessingException {
        //验证token格式是否正确
        boolean verifyValue;
        try {
            verifyValue = JWTUtil.verify(token, Constant.JWT_SECRET_BYTE);
        }
        catch (Exception e) {
            return false;
        }
        if (!verifyValue) {
            return false;
        }
        //验证token是否正确
        UserDetail user;
        try {
            String userJson = JWT.of(token).getPayloads().get("user", String.class);
            user = objectMapper.readValue(userJson, UserDetail.class);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (user == null || user.getUserId() == null) {
            return false;
        }
        String redisKey = Constant.REDIS_KEY_LOGIN_TOKEN + user.getUserId();
        List<Object> rightTokenList = redisTemplate.opsForList().range(redisKey, 0, -1);
        if (rightTokenList == null) {
            return false;
        }
        boolean isTokenExist = false;
        for (Object obj : rightTokenList) {
            String rightToken = (String) obj;
            LocalDateTime now = LocalDateTime.now();
            if (rightToken.equals(token)) {
                isTokenExist = true;
            }
            // 清理该用户过期的token
            String checkedUserJson = JWT.of(rightToken).getPayloads().get("user", String.class);
            UserDetail checkedUser = objectMapper.readValue(checkedUserJson, UserDetail.class);
            if (now.isAfter(checkedUser.getExpireDateTime())) {
                redisTemplate.opsForList().remove(redisKey, 0, rightToken);
            }
        }
        return isTokenExist;
        // 获取用户权限信息
//        List<GrantedAuthority> authorityList = new ArrayList<>(user.getAuthorities());
    }
}