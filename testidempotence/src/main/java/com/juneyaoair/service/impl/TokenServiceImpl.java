package com.juneyaoair.service.impl;

import com.juneyaoair.service.TokenService;
import com.juneyaoair.util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    private Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    private static final String TOKEN_NAME = "token";

    @Override
    public String checkToken(String id) {
        Long ttl = null;
        logger.info("执行TokenServiceImpl中的checkToken方法，id={}",id);
        if (!JedisUtil.exists(id)) {
            JedisUtil.set(id,"唯一标识",3);
                return "success";
        } else {
            ttl = JedisUtil.ttl(id);
        }
        return String.valueOf(ttl);
    }
}
