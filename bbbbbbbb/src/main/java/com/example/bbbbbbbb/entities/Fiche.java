package com.example.bbbbbbbb.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Fiche {
    @Id
    Long id;
    String nom;
    Date DN;
    String adresse;
    String email;
    String tel;
    String description;

    public Fiche(Long id, String nom, Date DN, String adresse, String email, String tel, String description) {
        this.id = id;
        this.nom = nom;
        this.DN = DN;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
        this.description = description;
    }

    public Fiche() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDN() {
        return DN;
    }

    public void setDN(Date DN) {
        this.DN = DN;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
