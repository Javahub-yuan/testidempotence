package com.juneyaoair.testjedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

public class TestJedis {
    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("172.22.0.58",6379);
        String testJedis = jedis.set("testJedis", "112");
        System.out.println("testJedis = " + testJedis);
        String test = jedis.get("testJedis");
        System.out.println("test = " + test);
        jedis.close();
    }

    @Test
    public void testJedisPool(){
        JedisPool pool = new JedisPool("172.22.0.58", 6379);
        Jedis jedis = pool.getResource();
        String test = jedis.get("test");
        String testJedis = jedis.get("testJedis");

        System.out.println("test = " + test);
        System.out.println("testJedis = " + testJedis);

        pool.close();
    }

    @Test
    public void test(){
        String replace = UUID.randomUUID().toString().replace("-", "");
        System.out.println("replace = " + replace);
    }
}
