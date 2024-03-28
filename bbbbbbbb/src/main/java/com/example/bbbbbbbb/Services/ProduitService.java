package com.example.bbbbbbbb.Services;

import com.example.bbbbbbbb.entities.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProduitService {

    public void Catalogue() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://extranet.apivia-courtage.fr/ws/rest/sante/individuelle/?cle=744f0b4c02ebf4e436a5346fb8339970&format=json&action=liste_produit";
        String jsonData = restTemplate.getForObject(url, String.class);

        // Désérialiser les données JSON en objet Java en utilisant ObjectMapper de Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Result result = objectMapper.readValue(jsonData, Result.class);
        afficherProduits(result.getList());
    }

    private void afficherProduits(Map<String, List<Produit>> produitsMap) {
        for (String categorie : produitsMap.keySet()) {
            if (categorie.equals("socles")){
                System.out.println("Catégorie : Produits");
                List<Produit> produits = produitsMap.get(categorie);
                for (Produit produit : produits) {
                    afficherProduit(produit);
                    System.out.println("--------------------------------------------------------------------");
                }
            }
            else if (categorie.equals("renforts")){
                List<Renfort> r=new ArrayList<>();
                System.out.println("Catégorie : "+categorie);
                List<Produit> renforts = produitsMap.get(categorie);
                for (Produit renf : renforts) {
                    r.add(new Renfort(renf.getLibelle(),renf.getCode()));
                }
                for (Renfort renf : r) {
                    affRenf(renf);
                    System.out.println("--------------------------------------------------------------------");
                }
            }
            else{
                List<Option> r=new ArrayList<>();
                System.out.println("Catégorie : "+categorie);
                List<Produit> renforts = produitsMap.get(categorie);
                for (Produit renf : renforts) {
                    r.add(new Option(renf.getLibelle(),renf.getCode()));
                }
                for (Option opt  : r) {
                    affOpt(opt);
                    System.out.println("--------------------------------------------------------------------");
                }
            }
        }
    }

    private void afficherProduit(Produit produit) {
        System.out.println("Libellé : " + produit.getLibelle());
        System.out.println("Code : " + produit.getCode());
        System.out.println("Formules :");
        for (Formule formule : produit.getFormules()) {
            afficherFormule(formule);
        }
    }
    private void affRenf(Renfort produit) {
        System.out.println("Libellé : " + produit.getLibelle());
        System.out.println("Code : " + produit.getCode());
    }

    private void affOpt(Option produit) {
        System.out.println("Libellé : " + produit.getLibelle());
        System.out.println("Code : " + produit.getCode());
    }

    private void afficherFormule(Formule formule) {
        System.out.println("\tLibellé : " + formule.getLibelle());
        System.out.println("\tCode : " + formule.getCode());
        System.out.println("\tResponsable : " + formule.getIsResponsable());
        if (formule.getOptions() != null && !formule.getOptions().isEmpty()) {
            System.out.println("\tOptions : " + String.join(", ", formule.getOptions()));
        }
        if (formule.getRenforts() != null && !formule.getRenforts().isEmpty()) {
            System.out.println("\tRenforts : " + String.join(", ", formule.getRenforts()));
        }
        System.out.println();
    }
}
