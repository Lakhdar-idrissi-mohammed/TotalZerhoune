package com.example.myapplication;

public class Carinfos {
    String Marque;
    String Immatriculation;
    String Ep;
    String prix ;

    public Carinfos() {}

    public Carinfos(String marque, String immatriculation, String ep, String prix) {
        Marque = marque;
        Immatriculation = immatriculation;
        Ep = ep;
        this.prix = prix;
    }

    public String getMarque() {
        return Marque;
    }



    public String getImmatriculation() {
        return Immatriculation;
    }



    public String getEp() {
        return Ep;
    }



    public String getPrix() {
        return prix;
    }


}
