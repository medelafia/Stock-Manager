package org.example.entities;

public class Fournisseur {
    private int idFournisseur ;
    private String nom ;
    private String tel ;
    private String addresse ;
    public Fournisseur(int idFournisseur, String nom, String tel, String addresse) {
        this.idFournisseur = idFournisseur;
        this.nom = nom;
        this.tel = tel;
        this.addresse = addresse;
    }

    public Fournisseur(String nom, String tel, String addresse) {
        this.nom = nom;
        this.tel = tel;
        this.addresse = addresse;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public String getNom() {
        return nom;
    }

    public String getTel() {
        return tel;
    }

    public String getAddresse() {
        return addresse;
    }
}
