package org.example.entities;


import java.sql.Date;
import java.util.List;

public class Commande {
    private int idCommande ;
    private Date date;
    private int idClient ;
    private String etat ;

    public Commande(Date date, int idClient, String enCour) {
        this.date = date ;
        this.idClient = idClient ;
        this.etat = enCour ;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public int getIdClient() {
        return idClient;
    }
    public Commande(int idCommande, Date date, int idClient, String etat) {
        this.idCommande = idCommande;
        this.date = date;
        this.idClient = idClient;
        this.etat = etat;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public Date getDate() {
        return date;
    }

}
