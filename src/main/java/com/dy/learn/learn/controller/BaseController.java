package com.dy.learn.learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {
    Logger logger=LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.OK)
    public String processException(Exception exception) {
        logger.info("error.....................");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxerrorxxxxxxxxxxxxxx");
        return "error";
    }


}
