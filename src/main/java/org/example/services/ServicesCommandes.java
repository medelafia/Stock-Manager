package org.example.services;
import org.example.entities.Commande;
import org.example.entities.Produit;
import org.example.repositories.RepoCommande;
import org.example.repositories.RepoProduit;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;
public class ServicesCommandes {
    private Scanner scanner;
    private RepoCommande repoCommande ;
    private RepoProduit repoProduit ;
    public ServicesCommandes() {
        this.scanner = new Scanner(System.in);
        this.repoCommande = new RepoCommande() ;
        this.repoProduit = new RepoProduit() ;
    }
    public void supprimerCommande() {
        consulterCommande();
        System.out.println("entrer l'id de commande");
        int id = scanner.nextInt() ;
        repoCommande.supprimer_Commande(id);
    }
    public void consulterCommande() {
        List<Commande> list = repoCommande.trouverToutesCommandes() ;
        if( list.size() > 0 ) {
            System.out.println(" -------------------------------------------");
            System.out.println("| idC  |   Date   |  idClient    |  etat    |");
            System.out.println("|______|__________|______________|__________|");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("|" + list.get(i).getIdCommande() + "|"
                        + list.get(i).getDate() + "|"
                        + list.get(i).getIdClient() + "|"
                        + list.get(i).getEtat()+ "|");
            }
        }else{
            System.out.println("la table des commandes est vide");
        }
    }
    public void passerCommande() {
        System.out.flush();
        System.out.println("1...................commande existant");
        System.out.println("2....................nouveau commande");
        switch (scanner.nextInt()) {
            case  1 : consulterCommande();
                System.out.println("entrer l'id de commande");
                int idCommande = scanner.nextInt() ;
                System.out.println("entrer l'id de le produit");
                int id = scanner.nextInt() ;
                System.out.println("entre la quantité de produit");
                int qte = scanner.nextInt() ;
                repoCommande.ajouterProduitACommande(id , qte , idCommande);
                Produit produit = repoProduit.getProduitAvecId(id);
                if(produit.getQte() >= qte) {
                    produit.setQte(produit.getQte() - qte);
                    repoProduit.modifierProduit(produit);
                }else {
                    System.out.println("la quantité insuffisante");
                }
                break;
            case  2 :
                System.out.println("entrer l'id de client");
                int idClient = scanner.nextInt() ;
                Clock clock = Clock.systemDefaultZone() ;
                Commande commande = new Commande(new Date(clock.millis()) ,idClient , "en cour") ;
                System.out.println("la creation de commande en cours");
                repoCommande.ajouteCommande(commande);
                List<Commande> list = repoCommande.trouverToutesCommandes() ;
                int idCommande_ = list.get(list.size() - 1 ).getIdCommande()  ;
                do {
                    System.out.println("entrer l'id de le produit");
                    int idProduit = scanner.nextInt() ;
                    System.out.println("entre la quantité de produit");
                    int qte_ = scanner.nextInt() ;
                    repoCommande.ajouterProduitACommande(idProduit , qte_ ,idCommande_);
                    Produit produit_ = repoProduit.getProduitAvecId(idProduit);
                    if( produit_.getQte()  >= qte_) {
                        produit_.setQte(produit_.getQte() - qte_);
                        repoProduit.modifierProduit(produit_);
                    }else {
                        System.out.println("la quantité insuffisante");
                    }
                    System.out.println("voulez vous ajouter un autre produit a la commande");
                }while (scanner.nextLine().compareToIgnoreCase("oui") == 0 ) ;
                break;
        }
    }
    public void modifierEtatCommande() {
        consulterCommande();
        System.out.println("entrer l'id de commande");
        int id = scanner.nextInt() ;
        System.out.println("entrer la nouvelle etat");
        String etat = scanner.nextLine() ;
        repoCommande.modifierEtatCommande(id , etat );
    }
    public void listProduitsDeCommande() {
        consulterCommande();
        System.out.println("entrer l'id de commande");
        int idCommande = scanner.nextInt() ;
        List<Integer> list = repoCommande.listProduitsCommande(idCommande) ;
        if(list.size()> 0) {
            System.out.println("la list des id's des produits dans la commande "+idCommande);
            for(int i=0 ; i<list.size() ; i++) {
                Produit produit = repoProduit.getProduitAvecId(list.get(i)) ;
                System.out.println(produit.getIdP() +"   " + produit.getNom() + "   " +produit.getDescription() +"   " + produit.getPrix() +"    " + produit.getQte() +"    "+ produit.getIdFournisseur()) ;
            }
        }
    }
    public void supprimerProduitDeCommande() {
        System.out.println("entrer l'id de commande");
        int idCommande = scanner.nextInt() ;
        System.out.println("entrer l'id de produit");
        int idProduit = scanner.nextInt() ;
        Produit produit = repoProduit.getProduitAvecId(idProduit) ;
        produit.setQte(produit.getQte() + repoCommande.getQteProduit(idProduit , idCommande ));
        repoProduit.modifierProduit(produit);
        repoCommande.supprimerProduitDeCommande(idCommande , idProduit);
    }
}
