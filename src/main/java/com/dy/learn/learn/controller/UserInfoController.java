package com.dy.learn.learn.controller;

import com.dy.learn.learn.dao.entity.UserInfo;
import com.dy.learn.learn.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    Logger logger=LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping("get/{userId}")
    public Object get(@PathVariable("userId") Integer userId,ModelAndView modelAndView){
        logger.info("get userId:{}",userId);
        UserInfo userInfo=userInfoService.getUserInfoById(userId);
//        modelAndView.addObject("userInfo",userInfo);
        return userInfo;
    }





    @ResponseBody
    @RequestMapping("update/{userId}")
    public Object update(@PathVariable("userId") Integer userId,String userName){
        UserInfo user=new UserInfo();
        user.setId(userId);
        user.setUsername(userName);
        int res=userInfoService.getUserInfo(user);
        return res>0?"ok":"fail";
    }

}
