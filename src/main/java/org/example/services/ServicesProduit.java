package org.example.services;

import org.example.entities.Produit;
import org.example.repositories.RepoProduit;

import java.util.List;
import java.util.Scanner;

public class ServicesProduit {
    private Scanner scanner ;
    private RepoProduit repoProduit ;
    public ServicesProduit( ) {
        this.scanner = new Scanner(System.in) ;
        this.repoProduit = new RepoProduit() ;
    }
    public void ajouterProduit() {
        System.out.println("entrer le nom de produit");
        String nom = scanner.nextLine() ;
        System.out.println("entrer le description de produit");
        String description = scanner.nextLine() ;
        System.out.println("entrer le prix de produit");
        float prix = scanner.nextFloat() ;
        System.out.println("entrer la quantité de produit");
        int qte = scanner.nextInt() ;
        System.out.println("entrer le nom de fournisseur");
        int idF = scanner.nextInt() ;
        Produit produit1 = new Produit(nom, description , prix , qte , idF );
        repoProduit.ajouteProduit(produit1);
    }
    public void supprimerProduit() {
        consulterProduits();
        System.out.println("entrer l'id de produit");
        int id = scanner.nextInt() ;
        repoProduit.supprimerProduit(id);
    }
    public void consulterProduits() {
        List<Produit> produits = repoProduit.trouverToutesProduits() ;
        if(produits.size() != 0 ) {
            System.out.println(" --------------------------------------------------------------------");
            System.out.println("| id   |   nom   |  description |  prix   |    qte   |  fournisseur  |");
            System.out.println("|______|_________|______________|_________|__________|_______________|");
            for (int i = 0; i < produits.size(); i++) {
                System.out.println("|" + produits.get(i).getIdP() + "|"
                        + produits.get(i).getNom() + "|"
                        + produits.get(i).getDescription() + "|"
                        + produits.get(i).getPrix() + "|"
                        + produits.get(i).getQte() + "|"
                        + produits.get(i).getIdFournisseur() + "|"
                );
            }
        }else {
            System.out.println("la table des produits est vide");
        }
    }
    public void modifierProduit() {
        consulterProduits();
        System.out.println("entrer l'id de produit");
        int id = scanner.nextInt() ;
        System.out.println("entrer le nouveau nom de produit");
        String nom = scanner.nextLine() ;
        System.out.println("entrer le nouveau description de produit");
        String description = scanner.nextLine() ;
        System.out.println("entrer le nouveau prix de produit");
        float prix = scanner.nextFloat() ;
        System.out.println("entrer la nouveau quantité de produit");
        int qte = scanner.nextInt() ;
        System.out.println("entrer l'id de fournisseur'");
        int idF = scanner.nextInt() ;
        Produit produit = new Produit(id ,nom , description , prix , qte , idF );
        repoProduit.modifierProduit(produit);
    }
}
