package com.dy.learn.learn.controller;

import com.dy.learn.learn.mq.MQProducerService;
import com.dy.learn.learn.mq.QueueEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("jms")
public class JsmController extends  BaseController {

    @Autowired
    private MQProducerService mqProducerService;

    @ResponseBody
    @RequestMapping(value = "sendPay/{msg}",method = RequestMethod.POST)
    public Object sendPay(@PathVariable("msg") String msg){
        mqProducerService.sendQueueMessage(QueueEnum.PayQueue,msg);
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "sendLogin/{msg}",method = RequestMethod.POST)
    public Object sendLogin(@PathVariable("msg") String msg){
        mqProducerService.sendTopicMessage(QueueEnum.LoginQueue,msg);
        return "ok";
    }

}
