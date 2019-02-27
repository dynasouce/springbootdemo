package com.dy.learn.learn.controller;


import com.dy.learn.learn.nosql.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("set/{key}/{val}")
    public Object set(@PathVariable("key") String key,@PathVariable("val") String val){
        redisUtils.set(key,val);
        return "ok";
    }

    @RequestMapping("get/{key}")
    public Object get(@PathVariable("key") String key){
        return redisUtils.get(key);
    }

    @RequestMapping("del/{key}")
    public Object del(@PathVariable("key") String key){
        return redisUtils.delete(key);
    }

}
