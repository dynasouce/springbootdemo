package com.dy.learn.learn.service.impl;

import com.dy.learn.learn.dao.entity.UserInfo;
import com.dy.learn.learn.dao.mapper.UserInfoMapper;
import com.dy.learn.learn.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfoById(Integer userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int getUserInfo(UserInfo userInfo) {
        int res=this.userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return res;
    }


}
