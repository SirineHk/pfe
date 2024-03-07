package com.example.bbbbbbbb.controllers;

import com.example.bbbbbbbb.Services.UserService;
import com.example.bbbbbbbb.entities.Utilisateur;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/aff")
    @PermitAll
    public String aff() {
        return userService.aff();
    }

    @GetMapping("/auth")
    //@PermitAll
    public String authentif() throws SQLException, IOException, ClassNotFoundException {
        return userService.authentification();
    }
    @GetMapping("/compte")
    //@PermitAll
    public String newCompte() throws SQLException, IOException{
        return userService.creerCompte();
    }

    @GetMapping("/users")
    public List<Utilisateur> listUsers() throws SQLException, ClassNotFoundException {
        List<Utilisateur> users=userService.listUsers();
        return users;
    }

    @GetMapping("/active")
    public String activer() throws SQLException, ClassNotFoundException {
        return userService.actiDesa();
    }
    @GetMapping("/modif")
    public String modifier() throws SQLException, ClassNotFoundException {
        return userService.update();
    }


}
