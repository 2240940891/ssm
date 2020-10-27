package com.huayu.redis;


import redis.clients.jedis.Jedis;

public class Rediscon {

    private static Jedis jedis;
    public static  Jedis connectionTest() {
        jedis = new Jedis("127.0.0.1", 6379);//redis的地址以及连接端口
        jedis.auth("han.com");  //开启密码验证（配置文件中为 requirepass helloworld）的时候需要执行该方法
        return jedis;
    }
}