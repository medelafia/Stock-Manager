package org.example.services;

import org.example.entities.Client;
import org.example.repositories.RepoClient;

import java.util.List;
import java.util.Scanner;

public class ServicesClients {
    private Scanner scanner ;
    private RepoClient repoClient ;
    public ServicesClients( ) {
        this.scanner = new Scanner(System.in) ;
        this.repoClient = new RepoClient() ;
    }
    public void ajouterClient() {
        System.out.println("entrer le nom de client");
        String nom = scanner.nextLine() ;
        System.out.println("entrer le prenom de client");
        String prenom = scanner.nextLine() ;
        System.out.println("entrer l'email de client");
        String email = scanner.nextLine() ;
        System.out.println("entrer le tel de client");
        String tel = scanner.nextLine() ;
        System.out.println("entrer l'adresse de client");
        String adresse = scanner.nextLine() ;
        Client client = new Client(nom , prenom , email , tel , adresse);
        repoClient.ajoute_client(client);
    }
    public void supprimerClient() {
        consulterClients();
        System.out.println("entrer l'id de client");
        int id = scanner.nextInt() ;
        repoClient.supprimer_client(id);
    }
    public void consulterClients() {
        List<Client> clients = repoClient.trouverToutesClients() ;
        if( clients.size() != 0 ) {
            System.out.println(" --------------------------------------------------------------------");
            System.out.println("| id   |   nom   |  prenom      |  email  |    tel   |  addresse     |");
            System.out.println("|______|_________|______________|_________|__________|_______________|");
            for( int i = 0 ; i < clients.size() ; i++) {
                System.out.println("|"+clients.get(i).getIdClient()+"|"
                        +"|"+clients.get(i).getNom()+"|"
                        +"|"+clients.get(i).getPrenom()+"|"
                        +"|"+clients.get(i).getEmail()+"|"
                        +"|"+clients.get(i).getTel()+"|"
                        +"|"+clients.get(i).getAddresse()+"|"
                );
            }
        }else {
            System.out.println("la table des clients est vide");
        }

    }
    public void modifierClient() {
        consulterClients();
        System.out.println("entrer l'id de client");
        int id = scanner.nextInt();
        System.out.println("entrer le nouveau nom de client");
        String nom = scanner.nextLine();
        System.out.println("entrer le nouveau prenom de client");
        String prenom = scanner.nextLine();
        System.out.println("entrer le nouveau email de client");
        String email = scanner.nextLine();
        System.out.println("entrer la nouveau tel de client");
        String tel = scanner.nextLine() ;
        System.out.println("entrer le nouveau addresse de client");
        String adresse = scanner.nextLine();
        Client client = new Client(id , nom , prenom , email , tel , adresse ) ;
        repoClient.modifier_client(client);
    }
}

