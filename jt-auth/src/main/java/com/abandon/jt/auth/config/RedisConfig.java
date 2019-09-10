package com.abandon.jt.auth.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class RedisConfig {
    @NacosValue(value = "${redis.maxTotal:100}", autoRefreshed = true)
    private Integer redisMaxTotal;

    @NacosValue(value = "${redis.node.host:94.191.101.181}", autoRefreshed = true)
    private String redisNodeHost;

    @NacosValue(value = "${redis.node.port:6379}", autoRefreshed = true)
    private Integer redisNodePort;

    @NacosValue(value = "${redis.node.password:2018NewLife.}", autoRefreshed = true)
    private String redisPassword;

    @NacosValue(value = "${redis.node.timeout:3000}", autoRefreshed = true)
    private Integer timeout;

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisMaxTotal);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool getJedisPool() {    // 省略第一个参数则是采用 Protocol.DEFAULT_DATABASE
        return new JedisPool(jedisPoolConfig(), redisNodeHost, redisNodePort, timeout, redisPassword);
    }

    @Bean
    public ShardedJedisPool shardedJedisPool() {
        List<JedisShardInfo> jedisShardInfo = new ArrayList<JedisShardInfo>();
        jedisShardInfo.add(new JedisShardInfo(redisNodeHost, redisNodePort));
        return new ShardedJedisPool(jedisPoolConfig(), jedisShardInfo);
    }
}
