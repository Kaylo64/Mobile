package com.example.problmecontacts;

public class Contact {
    private String nom;
    private String courriel;
    private String telephone;

    public Contact(String nom, String courriel, String telephone) {
        this.nom = nom;
        this.courriel = courriel;
        this.telephone = telephone;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getCourriel() {
        return courriel;
    }
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    @Override
    public String toString() {
        return "Contact{" +
                "nom='" + nom + '\'' +
                ", courriel='" + courriel + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
