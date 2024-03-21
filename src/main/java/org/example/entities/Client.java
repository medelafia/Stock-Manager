package org.example.entities;

public class Client {
    private int idClient ;


    private String nom ;
    private String prenom ;
    private String email ;
    private String tel ;
    private String addresse ;
    public Client(int idClient, String nom, String prenom, String email, String tel, String addresse) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.addresse = addresse;
    }
    public Client(String nom, String prenom, String email, String tel, String addresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.addresse = addresse;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getEmail() {
        return email;
    }
    public String getTel() {
        return tel;
    }

    public String getAddresse() {
        return addresse;
    }
}