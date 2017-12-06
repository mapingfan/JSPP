package com.whu.Jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtils {
    private static JedisPool pool = null;
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource");
        String host = resourceBundle.getString("host");
        String port = resourceBundle.getString("port");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(30);//最大闲置个数
        poolConfig.setMinIdle(10);
        poolConfig.setMaxTotal(50);//最大连接数
        pool = new JedisPool(poolConfig, host, Integer.parseInt(port));
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
