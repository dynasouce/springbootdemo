package com.dy.learn.learn.service;


import com.dy.learn.learn.dao.entity.UserInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserInfoService {

    UserInfo getUserInfoById(Integer id);

    @Transactional
    int getUserInfo(UserInfo userInfo);
}
