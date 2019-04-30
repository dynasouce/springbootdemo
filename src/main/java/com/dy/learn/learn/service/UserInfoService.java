package com.dy.learn.learn.service;


import com.dy.learn.learn.bean.Query.UserInfoQuery;
import com.dy.learn.learn.bean.ResultPage;
import com.dy.learn.learn.dao.entity.UserInfo;


public interface UserInfoService {

    UserInfo getUserInfoById(Integer id);

    int getUserInfo(UserInfo userInfo);

    void insertUserInfo(UserInfo userInfo);

    ResultPage<UserInfo> findUserInfoPage(UserInfoQuery query);
}
