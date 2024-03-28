package com.example.bbbbbbbb.controllers;

import com.example.bbbbbbbb.Services.ProduitService;
import com.example.bbbbbbbb.entities.Renfort;
import com.example.bbbbbbbb.entities.Result;
import com.example.bbbbbbbb.entities.Produit;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/prods")
public class ProduitController {
    private final ProduitService produitService;

    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/cat")
    @PermitAll
    void update() throws SQLException, ParseException, ClassNotFoundException, JAXBException, JsonProcessingException {
        produitService.Catalogue();
    }
}
