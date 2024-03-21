package org.example.services;

import org.example.entities.Client;
import org.example.entities.Fournisseur;
import org.example.repositories.RepoFournisseur;

import java.util.List;
import java.util.Scanner;

public class ServicesFournisseur {
    private final Scanner scanner ;
    private RepoFournisseur repoFournisseur ;
    public ServicesFournisseur( ) {
        this.scanner = new Scanner(System.in) ;
        this.repoFournisseur = new RepoFournisseur() ;
    }
    public void ajouterFournisseur() {
        System.out.println("entrer le nom de fournisseur");
        String nom = scanner.nextLine() ;
        System.out.println("entrer le tel de fournisseur");
        String tel = scanner.nextLine() ;
        System.out.println("entrer l'adresse de fournisseur");
        String adresse = scanner.nextLine() ;
        Fournisseur fournisseur = new Fournisseur(nom , tel , adresse ) ;
        repoFournisseur.ajoute_Fournisseur(fournisseur);
    }
    public void supprimerFournisseur() {
        System.out.println("entrer l'id de fournisseur");
        int id = scanner.nextInt() ;
        repoFournisseur.supprimer_Fournisseur(id);
    }
    public void consulterFournisseurs() {
        List<Fournisseur> list = repoFournisseur.trouverToutesFournisseurs() ;
        if( list.size() != 0) {
            System.out.println(" --------------------------------------------");
            System.out.println("| id   |   nom   |    tel   |  addresse     |");
            System.out.println("|______|_________|__________|_______________|");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("|  " + list.get(i).getIdFournisseur() + "  |   "
                        + list.get(i).getNom() + "   |    "
                        + list.get(i).getTel() + "   |    "
                        + list.get(i).getAddresse() + "    |    ");
            }
        }else {
            System.out.println("la table des fournisseurs est vide");
        }
    }
    public void modifierFournisseur() {
        consulterFournisseurs();
        System.out.println("entrer l'id de fournisseur");
        int id = scanner.nextInt();
        System.out.println("entrer le nouveau nom de fournisseur");
        String nom = scanner.nextLine();
        System.out.println("entrer la nouveau tel de client");
        String tel = scanner.nextLine() ;
        System.out.println("entrer le nouveau addresse de client");
        String adresse = scanner.nextLine();
        Fournisseur fournisseur = new Fournisseur(id , nom , tel , adresse );
        repoFournisseur.modifier_Fournisseur(fournisseur);
    }
}
