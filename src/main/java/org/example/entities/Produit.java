package org.example.entities;

public class Produit {
    private int idP ;
    private String nom ;
    private String description ;
    private float prix ;
    private int qte ;
    private int idFournisseur ;

    public Produit(int idP, String nom, String description, float prix, int qte, int idFournisseur) {
        this.idP = idP;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.qte = qte;
        this.idFournisseur = idFournisseur;
    }
    public Produit(String nom, String description, float prix, int qte, int idFournisseur) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.qte = qte;
        this.idFournisseur = idFournisseur;
    }
    public int getIdFournisseur() {
        return idFournisseur;
    }

    public int getIdP() {
        return idP;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

    public int getQte() {
        return qte;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
