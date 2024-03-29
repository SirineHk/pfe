package com.example.bbbbbbbb.entities;

import jakarta.persistence.*;

@Entity
//@Table(name="ROLE")
public class Role {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String nom;

    public Role(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Role() {

    }

    public long getId() {
        return this.id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
