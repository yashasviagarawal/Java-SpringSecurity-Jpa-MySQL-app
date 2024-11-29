package com.yash.SpringSecurityAuthApp.services;

import com.yash.SpringSecurityAuthApp.entity.ResponseModel;
import com.yash.SpringSecurityAuthApp.entity.UserInfo;
import com.yash.SpringSecurityAuthApp.entity.UserInfoDetails;
import com.yash.SpringSecurityAuthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> user = userRepository.findByUsername(username);
        return user.map(UserInfoDetails::new).orElseThrow(()->
                new UsernameNotFoundException("No User Found with username: "+username)
        );
    }


    @Override
    public UserInfo createUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userRepository.save(userInfo);
    }

    @Override
    public UserInfo getUser(UserInfo userInfo) {
        return null;
    }
}
