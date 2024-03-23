package org.example;
import org.example.services.*;
import java.util.Scanner;
public class Main {
    public static void menu() {
        System.out.flush();
        System.out.println("----------------------Menu----------------------");
        System.out.println("1...........................gestion des produits");
        System.out.println("2............................gestion des clients");
        System.out.println("3.......................gestion des fournisseurs");
        System.out.println("4..........................gestion des commandes");
        System.out.println("5...........................................exit");
    }
    public static void services() {
        System.out.println("1............................. ajouter");
        System.out.println("2............................. supprimer");
        System.out.println("3............................. consulter");
        System.out.println("4............................. modifier");
    }
    public static void pressEnterToContinue()
    {
        System.out.println("\n>>>>   Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in) ;
        ServicesProduit servicesProduit = new ServicesProduit() ;
        ServicesClients servicesClients = new ServicesClients() ;
        ServicesCommandes servicesCommandes = new ServicesCommandes() ;
        ServicesFournisseur servicesFournisseur = new ServicesFournisseur() ;
        ServiceLogin serviceLogin = new ServiceLogin() ;
        serviceLogin.Login();
        while(true) {
            System.out.flush();
            menu();
            System.out.println("choisie le service");
            switch (scanner.nextInt() ) {
                case 1 : while (true) {
                        boolean stop = false ;
                        services();
                        System.out.println("5......................exporter");
                        System.out.println("6.........................exit");
                        System.out.flush();
                        switch (scanner.nextInt()) {
                            case 1 : servicesProduit.ajouterProduit();
                                break;
                            case 2 : servicesProduit.supprimerProduit();
                                break ;
                            case 3 : servicesProduit.consulterProduits();
                                break;
                            case 4 : servicesProduit.modifierProduit();
                                break;
                            case 5 : servicesProduit.exporterProduits();
                                break;
                            case 6 :
                                stop = true ;
                                break;
                        }
                        if(stop) break;
                        pressEnterToContinue();
                    }
                    break;
                case 2 :
                    while (true) {
                        boolean stop = false ;
                        services();
                        System.out.println("5.......................exporter");
                        System.out.println("6.........................exit");
                        System.out.flush();
                        switch (scanner.nextInt()) {
                            case 1 : servicesClients.ajouterClient();
                                break;
                            case 2 : servicesClients.supprimerClient();
                                break ;
                            case 3 : servicesClients.consulterClients();
                                break;
                            case 4 : servicesClients.modifierClient();
                                break;
                            case 5 : servicesClients.exporterClients();
                                    break;
                            case 6 : stop = true ;
                                break ;
                        }
                        if(stop) break;
                        pressEnterToContinue();
                    }
                    break;
                case 3 :
                    while (true) {
                        boolean stop = false ;
                        services();
                        System.out.println("5.......................exporter");
                        System.out.println("6.........................exit");
                        System.out.flush();
                        switch (scanner.nextInt()) {
                            case 1 : servicesFournisseur.ajouterFournisseur();
                                break;
                            case 2 : servicesFournisseur.supprimerFournisseur();
                                break ;
                            case 3 : servicesFournisseur.consulterFournisseurs();
                                break;
                            case 4 : servicesFournisseur.modifierFournisseur();
                                break;
                            case 5 : servicesFournisseur.exporterFournisseurs();
                                break ;
                            case 6 : stop = true ;
                                break ;
                        }
                        if(stop) break;
                        pressEnterToContinue();
                    }
                    break;
                case 4 :
                    while (true) {
                        boolean stop = false ;
                        services();
                        System.out.println("5...............list de produits d'un commande");
                        System.out.println("6....................supprimer un produit dans commande");
                        System.out.println("7.......................exporter");
                        System.out.println("8.......................importer commandes");
                        System.out.println("9.......................exit");
                        System.out.flush();
                        switch (scanner.nextInt()) {
                            case 1 : servicesCommandes.passerCommande();
                                break;
                            case 2 : servicesCommandes.supprimerCommande();
                                break ;
                            case 3 : servicesCommandes.consulterCommande();
                                break;
                            case 4 : servicesCommandes.modifierEtatCommande();
                                break;
                            case 5 :
                                servicesCommandes.listProduitsDeCommande();
                                break;
                            case 6 :  servicesCommandes.supprimerProduitDeCommande();
                                break;
                            case 7 : servicesCommandes.exporterCommandes();
                                break ;
                            case 8 : servicesCommandes.importerCommandes();
                                break;
                            case 9: stop = true ;
                                break;
                        }
                        if(stop) break;
                        pressEnterToContinue();
                    }
                    break ;
                case 5 :
                    System.exit(0);
                    break;
            }
            System.out.flush();
        }

    }
}