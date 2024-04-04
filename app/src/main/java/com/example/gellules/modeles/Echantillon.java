package com.example.gellules.modeles;

public class Echantillon {

    protected String code;

    protected String libelle;

    protected String quantiteStock;

    public Echantillon(String code, String libelle, String quantiteStock) {
        this.code = code;
        this.libelle = libelle;
        this.quantiteStock = quantiteStock;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getQuantiteStock() {
        return quantiteStock;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setQuantiteStock(String quantiteStock) {
        this.quantiteStock = quantiteStock;
    }
}
