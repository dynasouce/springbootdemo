package com.dy.learn.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "freemarkerTest")
@RequestMapping(value = "freemarkerTest")
public class FreemarkerTestController {

    @RequestMapping("test1")
    public ModelAndView test1(ModelAndView modelAndView){
        modelAndView.setViewName("test1");
        List<String> userList=new ArrayList<String>();
        userList.add("admin");
        userList.add("user1");
        userList.add("user2");
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

}
