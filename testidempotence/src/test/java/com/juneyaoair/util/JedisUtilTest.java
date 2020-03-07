package com.juneyaoair.util;

import com.juneyaoair.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JedisUtilTest {

    @Test
    public void set() {
        String set = JedisUtil.set("1", "101");
        System.out.println("set = " + set);
        //设值，有过期时间
        String set1 = JedisUtil.set("2", "2", 10);
        System.out.println("set1 = " + set1);
    }

    @Test
    public void delTest(){
        Long del = JedisUtil.del("1");
        System.out.println("del = " + del);
    }

    @Test
    public void expireTest(){
        Long expire = JedisUtil.expire("1", 20);
        System.out.println("expire = " + expire);
    }

    @Test
    public void ttlTest(){
        set();
        expireTest();
        Long ttl = JedisUtil.ttl("1");
        System.out.println("ttl = " + ttl);
    }
}