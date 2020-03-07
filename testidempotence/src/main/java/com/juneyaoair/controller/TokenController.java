package com.juneyaoair.controller;

import com.juneyaoair.annotation.ApiIdempotent;
import com.juneyaoair.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("token")
@ResponseBody
public class TokenController {
    private Logger logger = LoggerFactory.getLogger(TokenController.class);
    @Autowired
    private TokenService tokenService;

    @ApiIdempotent
    @RequestMapping(value = "test")
    public String testIdempotent() {
        logger.info("执行了TokenController中的testIdempotent方法");
        return "success";
    }
}
