package com.example.bbbbbbbb.controllers;

import com.example.bbbbbbbb.Services.FicheService;
import com.example.bbbbbbbb.Services.GroupService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/fiches")
public class FicheController {
    private final FicheService ficheService;

    @Autowired
    public FicheController(FicheService ficheService) {
        this.ficheService = ficheService;
    }
    @GetMapping("/upd")
    @PermitAll
    String update() throws SQLException, ParseException, ClassNotFoundException {
        return ficheService.update();
    }
}
