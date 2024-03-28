package com.example.bbbbbbbb.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Formule {
    @Id
    private Long id;
    private String libelle;
    private String code;
    private Boolean isResponsable;

    @ElementCollection
    private List<String> options;

    @ElementCollection
    private List<String> renforts;

    @ManyToOne
    private Produit produit;

    public Formule(Long id, String libelle, String code, Boolean isResponsable, List<String> options, List<String> renforts) {
        this.id = id;
        this.libelle = libelle;
        this.code = code;
        this.isResponsable = isResponsable;
        this.options = options;
        this.renforts = renforts;
    }

    public Formule() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIsResponsable() {
        return isResponsable;
    }

    public void setIsResponsable(Boolean responsable) {
        isResponsable = responsable;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getRenforts() {
        return renforts;
    }

    public void setRenforts(List<String> renforts) {
        this.renforts = renforts;
    }
}
