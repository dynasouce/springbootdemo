package com.dy.learn.learn.nosql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void set(String key,String val,long timeout){
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        ops.set(key, val,timeout);
    }

    public void set(String key,String val){
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        ops.set(key, val);
    }

    public String get(String key){
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        return ops.get(key);
    }

    public boolean delete(String key){
        return this.redisTemplate.delete(key);
    }

}
