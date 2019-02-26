package com.dy.learn.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.dy.learn.learn.bean.UserInfo;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 这是一个进入首页的备注
 */
@RestController
public class IndexController {

    Logger logger=LoggerFactory.getLogger(IndexController.class);

    private String name;



    /**
     * hello springboot
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "hello")
    public Object login() {
        try {
            logger.info("into hello........");

            JSONObject json=new JSONObject();
            json.put("name","刘备");
            json.put("age",26);
            json.put("address","jx");
            logger.info("return web:{},other msg:{}",json.toJSONString(),"jack");

            Map<String,Object> map=new HashMap<String,Object>();
            map.put("name","zhangSan");
            map.put("age",20);

            UserInfo userInfo=mapToBean(map,UserInfo.class);
            String name=userInfo.getName();
            int age=userInfo.getAge();

            logger.info("lookfor userInfo,userName:{},age:{}",name,age);

            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private <T> T mapToBean(Map<String,Object> map,Class<T> clazz) throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        if (map==null)
            return null;
        if (clazz==null)
            return null;

        T t=clazz.newInstance();
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()){
            String key=keys.next();
            Object val=map.get(key);
            if (val!=null){
                Field field = clazz.getDeclaredField(key);
                if (field!=null){
                    boolean isAccessible=field.isAccessible();
                    if (!isAccessible)
                        field.setAccessible(true);
                    Class typeClass = field.getType();
                    String valTypeName=val.getClass().getSimpleName();
                    String beanTypeName=typeClass.getSimpleName();
                    if (valTypeName.equalsIgnoreCase(beanTypeName)){
                        field.set(t,val);
                    }
                    field.setAccessible(isAccessible);
                }
            }
        }

        return t;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}