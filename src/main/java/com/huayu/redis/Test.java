package com.huayu.redis;

import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

public class Test {

    public void keyTest() throws UnsupportedEncodingException {
        Jedis jedis = Rediscon.connectionTest();
        System.out.println("连接成功");
        System.out.println("服务正在运行: " + jedis.ping());

        System.out.println(jedis.flushDB());// 清空数据

        // 判断key否存在
        System.out.println(jedis.exists("foo"));

        jedis.set("key", "values");
        jedis.set("key2", "values");
        System.out.println(jedis.exists("key"));// 判断是否存在

        // 设置60秒后该key过期
        jedis.expire("key", 60);


        // key有效毫秒数
        System.out.println(jedis.pttl("key"));

        // 移除key的过期时间
        jedis.persist("key");

        // 获取key的类型, "string", "list", "set". "none" none表示key不存在
        System.out.println("type: " + jedis.type("key"));

        // 导出key的值
        byte[] bytes = jedis.dump("key");
        System.out.println(bytes[17]);

        // 将key重命名
        jedis.renamenx("key", "keytest");
        System.out.println("key是否存在: " + jedis.exists("key"));// 判断是否存在
        System.out.println("keytest是否存在: " + jedis.exists("keytest"));// 判断是否存在

        // 查询匹配的key
        // KEYS       * 匹配数据库中所有 key 。
        // KEYS       h?llo 匹配 hello ， hallo 和 hxllo 等。
        // KEYS       h*llo 匹配 hllo 和 heeeeello 等。
        // KEYS       h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
        // 特殊符号用 \ 隔开。
        Set<String> set = jedis.keys("k*");
        System.out.println(set);

        // 删除key
        jedis.del("key");
        System.out.println(jedis.exists("key"));
    }

    public void stringTest() {
        Jedis jedis = Rediscon.connectionTest();
        System.out.println("连接成功");
        System.out.println("服务正在运行: " + jedis.ping());

        jedis.set("hello", "hello");
        System.out.println(jedis.get("hello"));

    // 使用append 向字符串后面添加
        jedis.append("hello", " world");
        System.out.println(jedis.get("hello"));

    // set覆盖字符串
        jedis.set("hello", "123");
        System.out.println(jedis.get("hello"));

    // 设置过期时间
        jedis.setex("hello2", 2, "world2");
        System.out.println(jedis.get("hello2"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println(jedis.get("hello2"));

    // 一次添加多个key-value对
        jedis.mset("a", "1", "b", "2");

    // 获取a和b的value
        List<String> valus = jedis.mget("a", "b");
        System.out.println(valus);

    // 批量删除
        jedis.del("a", "b");
        System.out.println(jedis.exists("a"));
        System.out.println(jedis.exists("b"));

        System.out.println(jedis.flushDB());// 清空数据
    }

}
