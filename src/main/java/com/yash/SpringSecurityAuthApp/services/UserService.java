package com.yash.SpringSecurityAuthApp.services;

import com.yash.SpringSecurityAuthApp.entity.ResponseModel;
import com.yash.SpringSecurityAuthApp.entity.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserInfo createUser(UserInfo userInfo);

    UserInfo getUser(UserInfo userInfo);
}
