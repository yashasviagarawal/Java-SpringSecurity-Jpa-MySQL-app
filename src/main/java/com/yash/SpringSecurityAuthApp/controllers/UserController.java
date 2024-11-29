package com.yash.SpringSecurityAuthApp.controllers;

import com.yash.SpringSecurityAuthApp.entity.AuthRequest;
import com.yash.SpringSecurityAuthApp.entity.ResponseModel;
import com.yash.SpringSecurityAuthApp.entity.UserInfo;
import com.yash.SpringSecurityAuthApp.services.JWTService;
import com.yash.SpringSecurityAuthApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcomeApi(){
        return "Welcome to the Application";
    }

    @PostMapping("/createUser")
    public ResponseEntity<ResponseModel> createUser(@RequestBody UserInfo userInfo){
        ResponseModel responseModel = new ResponseModel();
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    @PostMapping("/getUser")
    public ResponseEntity<ResponseModel> getUser(@RequestBody Long id){
        ResponseModel responseModel = new ResponseModel();
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    @PostMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile(){
        return "This is User Profile";
    }

    @PostMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile(){
        return "This is Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),authRequest.getPassword()
                ));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }
        else{
            throw new UsernameNotFoundException("Invalid User Details!!");
        }
    }
}
