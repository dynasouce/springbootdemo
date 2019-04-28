package com.dy.learn.learn.controller;


import com.dy.learn.learn.nosql.RedisUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "设置Key-Value到Redis中")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "key",value = "键",required = true,dataType = "string",paramType = "string"),
            @ApiImplicitParam(name = "val",value = "值",required = true,dataType = "string")}
    )
    @RequestMapping(value = "set/{key}/{val}",method = RequestMethod.POST)
    public Object set(@PathVariable("key") String key,@PathVariable("val") String val){
        redisUtils.set(key,val);
        return "ok";
    }

    @RequestMapping(value = "get/{key}",method = RequestMethod.GET)
    public Object get(@PathVariable("key") String key){
        return redisUtils.get(key);
    }

    @RequestMapping(value = "del/{key}",method = RequestMethod.DELETE)
    public Object del(@PathVariable("key") String key){
        return redisUtils.delete(key);
    }

}
