package com.digital.harrier.common.helper;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolHelper {

    public static JedisPool initPool(String host, int port, String auth,JedisPoolConfig jedisPoolConfig) {
        if (StringUtils.isEmpty(auth))
            return new JedisPool(jedisPoolConfig,host,port,2000);
        else
            return new JedisPool(jedisPoolConfig,host,port,2000,auth);
    }

    public static JedisPool initPool(String host,String auth,int port) {
        JedisPoolConfig jedisPoolConfig = getDefaultPoolConfigure();
        return initPool(host,port,auth,jedisPoolConfig);
    }

    public static JedisPool initPool(String host,String auth) {
        return initPool(host,auth,6379);
    }

    public static JedisPoolConfig getPoolConfigure(int max_total,int min_idle,int max_wait_millis) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(max_total);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setBlockWhenExhausted(true);
        jedisPoolConfig.setMaxWaitMillis(max_wait_millis);
        jedisPoolConfig.setMinIdle(min_idle);
        return jedisPoolConfig;
    }

    public static JedisPoolConfig getDefaultPoolConfigure() {
        return getPoolConfigure(10,2,300);
    }
}
