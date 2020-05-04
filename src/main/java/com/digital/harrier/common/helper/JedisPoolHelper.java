package com.digital.harrier.common.helper;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolHelper {
    public static JedisPool initPool(String host, int port, String auth) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setBlockWhenExhausted(true);
        jedisPoolConfig.setMaxWaitMillis(3000);
        jedisPoolConfig.setMinIdle(2);
        if (StringUtils.isEmpty(auth))
            return new JedisPool(jedisPoolConfig,host,port,2000);
        else
            return new JedisPool(jedisPoolConfig,host,port,2000,auth);

    }
}
