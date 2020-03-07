package com.juneyaoair.service.impl;

import com.juneyaoair.Application;
import com.juneyaoair.service.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TokenServiceImplTest {

    @Autowired
    private TokenService tokenService;

    @Test
    public void checkToken() {
        String uuid = tokenService.checkToken("UUID");
        System.out.println("uuid = " + uuid);

    }
}