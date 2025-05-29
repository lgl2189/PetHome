package com.pethome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.dto.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 26 23:40
 */

@SpringBootTest
public class PetHomeApplicationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws Exception {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(1);
        userDetail.setUserName("admin");
        userDetail.setUserPassword("123456");
        userDetail.setEmail("admin@pethome.com");
        String json = objectMapper.writeValueAsString(userDetail);
        System.out.println(json);
    }
}