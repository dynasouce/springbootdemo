package com.dy.learn.learn.controller;

import com.dy.learn.learn.bean.Result;
import com.dy.learn.learn.exception.CoreExceltion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@ControllerAdvice
public class BaseController {
    Logger logger=LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(CoreExceltion.class)
    @ResponseBody
    public Result<String> jsonErrorHandler(HttpServletRequest request, CoreExceltion ex){
        logger.info(">>>>>>>>>>>>>>>>:jsonErrorHandler................");
        Result<String> result = new Result<String>();
        result.setErrorMsg(ex.getMessage());
        result.setErrorCode(ex.getErrorCode());
        result.setData(null);
        result.setUrl(request.getRequestURL().toString());
        return result;
    }


}
