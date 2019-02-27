package com.dy.learn.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("file")
public class FileController extends BaseController {

    @RequestMapping("index")
    public String index(){
        logger.info("file index");
        return "file";
    }


    @ResponseBody
    @RequestMapping(value = "uploadFile",method = RequestMethod.POST)
    public Object uploadFile(MultipartFile file){
        logger.info("upload file");
        long fileSize=file.getSize();
        String fileName=file.getOriginalFilename();
        logger.info("fileSize:{},fileName:{}",fileSize,fileName);
        return "ok";
    }

}
