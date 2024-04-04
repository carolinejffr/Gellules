package com.example.gellules.modeles;

public class Mouvement
{
    private String code;
    private String libelle;
    private String type;
    private String date;
    private String quantite;

    public Mouvement(String code, String libelle, String type, String date, String quantite) {
        this.code = code;
        this.libelle = libelle;
        this.type = type;
        this.date = date;
        this.quantite = quantite;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }
}
