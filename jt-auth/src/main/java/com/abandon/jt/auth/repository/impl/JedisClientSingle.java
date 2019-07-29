package com.abandon.jt.auth.repository.impl;

import com.abandon.jt.auth.repository.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @createData: 2019-06-24 17:19
 * @author: LongJunTao
 * @Description:
 */
@Component
public class JedisClientSingle implements JedisClient {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get(key);
        jedis.close();
        return string;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.set(key, value);
        jedis.close();
        return string;
    }

    @Override
    public String hGet(String hKey, String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.hget(hKey, key);
        jedis.close();
        return string;
    }

    @Override
    public long hSet(String hKey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.hset(hKey, key, value);
        jedis.close();
        return result;
    }

    @Override
    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public long hDel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.hdel(hkey, key);
        jedis.close();
        return result;
    }

    @Override
    public long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }
}
