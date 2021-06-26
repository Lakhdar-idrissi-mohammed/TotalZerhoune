package com.example.myapplication;

public class Carinfos2 {
    String Marque;
    String Immatriculation;
    String Prestation;
    String prix ;

    public Carinfos2() {}

    public Carinfos2(String marque, String immatriculation, String ep, String prix) {
        Marque = marque;
        Immatriculation = immatriculation;
        Prestation = ep;
        this.prix = prix;
    }

    public String getMarque() {
        return Marque;
    }



    public String getImmatriculation() {
        return Immatriculation;
    }



    public String getPrestation() {
        return Prestation;
    }



    public String getPrix() {
        return prix;
    }


}