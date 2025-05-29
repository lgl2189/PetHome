package com.pethome.task;

import cn.hutool.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.constant.Constant;
import com.pethome.dto.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 11 15:36
 */

@Component
public class LoginTokenTask {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper;

    @Autowired
    public LoginTokenTask(RedisTemplate<String, Object> redisTemplate,
                          RedisConnectionFactory connectionFactory,
                          ObjectMapper objectMapper) {
        Assert.notNull(redisTemplate, "redisTemplate must not be null");
        Assert.notNull(connectionFactory, "connectionFactory must not be null");
        Assert.notNull(objectMapper, "objectMapper must not be null");
        this.redisTemplate = redisTemplate;
        this.connectionFactory = connectionFactory;
        this.objectMapper = objectMapper;
    }

    //秒 分 时 日 月 星期
    @Scheduled(cron = "0 0 */1 * * *")
    public void cleanVerificationToken() {
        try (RedisConnection connection = connectionFactory.getConnection()) {
            // 使用 SCAN 命令避免阻塞 Redis 服务器
            String pattern = Constant.REDIS_KEY_LOGIN_TOKEN + "*";
            Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions()
                    .match(pattern)
                    .count(100)
                    .build());
            while (cursor.hasNext()) {
                String key = new String(cursor.next());
                List<Object> tokenList = redisTemplate.opsForList().range(key, 0, -1);
                if (tokenList != null) {
                    for(Object tokenObj: tokenList) {
                        String tokenStr = (String) tokenObj;
                        String userJson = JWT.of(tokenStr).getPayloads().get("user", String.class);
                        UserDetail user = objectMapper.readValue(userJson, UserDetail.class);
                        if(LocalDateTime.now().isAfter(user.getExpireDateTime())){
                            redisTemplate.opsForList().remove(key, 0, tokenObj);
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            // 记录异常信息，避免任务中断
            e.printStackTrace();
        }
    }

}