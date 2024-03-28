package com.example.bbbbbbbb.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Produit {
    @Id
    private Long id;
    private String libelle;
    private String code;

    @OneToMany
    private List<Formule> formules;

    public Produit(String libelle, String code, List<Formule> formules) {
        this.libelle = libelle;
        this.code = code;
        this.formules = formules;
    }

    public Produit() {
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Formule> getFormules() {
        return formules;
    }

    public void setFormules(List<Formule> formules) {
        this.formules = formules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
