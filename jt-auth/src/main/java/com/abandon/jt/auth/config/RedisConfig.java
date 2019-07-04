package com.abandon.jt.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @createData: 2019-07-04 13:54
 * @author: LongJunTao
 * @Description:
 */
@Configuration
@PropertySource(value = "classpath:redis.properties")
public class RedisConfig {
    @Value("${redis.maxTotal}")
    private Integer redisMaxTotal;

    @Value("${redis.node.host}")
    private String redisNodeHost;

    @Value("${redis.node.port}")
    private Integer redisNodePort;

    @Value("${redis.node.password}")
    private String redisPassword;

    @Value("${redis.node.timeout}")
    private Integer timeout;

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisMaxTotal);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool getJedisPool(){    // 省略第一个参数则是采用 Protocol.DEFAULT_DATABASE
        JedisPool jedisPool = new JedisPool(jedisPoolConfig(), redisNodeHost, redisNodePort,timeout,redisPassword);
        return jedisPool;
    }

    @Bean
    public ShardedJedisPool shardedJedisPool() {
        List<JedisShardInfo> jedisShardInfo = new ArrayList<JedisShardInfo>();
        jedisShardInfo.add(new JedisShardInfo(redisNodeHost, redisNodePort));
        return new ShardedJedisPool(jedisPoolConfig(), jedisShardInfo);
    }
}
