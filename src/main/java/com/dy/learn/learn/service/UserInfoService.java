package com.dy.learn.learn.service;


import com.dy.learn.learn.dao.entity.UserInfo;
import com.dy.learn.learn.dataSource.DataSourceSelection;
import com.dy.learn.learn.enums.DataSourceType;
import org.springframework.transaction.annotation.Transactional;


public interface UserInfoService {

    UserInfo getUserInfoById(Integer id);

    int getUserInfo(UserInfo userInfo);

    void insertUserInfo(UserInfo userInfo);
}
