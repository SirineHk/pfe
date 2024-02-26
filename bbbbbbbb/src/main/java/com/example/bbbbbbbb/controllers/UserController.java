package com.example.bbbbbbbb.controllers;

import com.example.bbbbbbbb.Services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {
    private UserService s=new UserService();
    public String aff(){
        return  s.aff();
    }
}
