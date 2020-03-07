package com.juneyaoair.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    private static JedisPool jedisPool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);//最大连接数
        config.setMaxIdle(100);//最大空闲等待时间
        config.setMaxWaitMillis(10000);//最大等待时间

        jedisPool = new JedisPool(config,"192.168.136.141",6379);
    }

    //从连接池获取Jedis对象
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    /**
     * 并不是关闭连接，而是放回连接池，否则会报
     * redis.clients.jedis.exceptions.JedisException: Could not get a resource from the pool
     * @param jedis
     */
    private static void close(Jedis jedis){
        if (null != jedis) {
            jedis.close();
        }
    }


    /**
     * 设值
     * @param key
     * @param value
     * @return
     */
    public static String set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, value);
        } catch (Exception e) {
            logger.error("set key:{} value:{} error", key, value, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 设值，有过期时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public static String set(String key,String value,int expireTime){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setex(key,expireTime,value);
        } catch (Exception e) {
            logger.error("set key:{} value:{} expireTime:{} error",key,value,expireTime);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public static String get(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("get key:{} error",key);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 删除
     * @param key
     * @return
     */
    public static Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.del(key);
        } catch (Exception e) {
            logger.error("del key:{} error",key);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public static Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            logger.error("exists key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 设值key过期时间
     *
     * @param key
     * @param expireTime 过期时间, 单位: s
     * @return
     */
    public static Long expire(String key, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.expire(key.getBytes(), expireTime);
        } catch (Exception e) {
            logger.error("expire key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 获取剩余时间
     *
     * @param key
     * @return
     */
    public static Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.ttl(key);
        } catch (Exception e) {
            logger.error("ttl key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

}
