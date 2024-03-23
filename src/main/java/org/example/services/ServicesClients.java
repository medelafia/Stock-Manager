package org.example.services;

import org.example.entities.Client;
import org.example.repositories.RepoClient;
import java.io.*;
import java.sql.PreparedStatement;
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
        System.out.flush();
        System.out.println("ajouter par");
        System.out.println("1.............Lignes des commandes");
        System.out.println("2..............fichier existant");
        switch (scanner.nextInt()) {
            case 1 :    scanner.nextLine();
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
                        break;
            case 2 : System.out.println("entrer le chemin du fichier texte");
                    scanner.nextLine() ;
                    String filePath = scanner.nextLine();
                    FileInputStream file = null;
                    try {
                        file = new FileInputStream(filePath);
                        Scanner s = new Scanner(file);
                        System.out.println("Liste des client a partir du fichier 'Client.txt' \n");
                        System.out.print("ID|\tNom\t|\tprenom\t|email\t\t|Tel\t|\tadresse\t\n");
                        System.out.println("---------------------------------------------------------------------------");
                        while (s.hasNextLine() && s.hasNext()) {
                            int sid = Integer.parseInt(s.next());
                            String snom = s.next();
                            String sprenom = s.next();
                            String semail = s.next();
                            String stel = s.next();
                            String sadresse = s.next();
                            System.out.print(" " + sid + "|\t");
                            System.out.print(snom + "\t|\t");
                            System.out.print(sprenom + "\t|");
                            System.out.print(semail + "|");
                            System.out.print(stel + "\t|\t");
                            System.out.println(sadresse);
                            repoClient.ajoute_client(new Client(snom.replaceAll("_" , " ")
                                    ,sprenom.replaceAll("_" , " ")
                                    ,semail,
                                    stel,
                                    sadresse.replaceAll("_" , " ") ));
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
        }
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
                System.out.println("|\t"+clients.get(i).getIdClient()+"\t|\t"
                        +clients.get(i).getNom()+"\t|\t"
                        +clients.get(i).getPrenom()+"\t|\t"
                        +clients.get(i).getEmail()+"\t|\t"
                        +clients.get(i).getTel()+"\t|\t"
                        +clients.get(i).getAddresse()+"\t|"
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
        scanner.nextLine() ;
        scanner.nextLine() ;
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
    public void exporterClients() {
        File file = new File("clients.txt") ;
        try (Writer writer = new FileWriter(file)){
            List<Client> clients = repoClient.trouverToutesClients() ;
            for(int i=0 ; i<clients.size() ; i++) {
                writer.write(clients.get(i).getIdClient()+"\t\t"+
                        clients.get(i).getNom().replaceAll(" " ,"_")+"\t\t" +
                        clients.get(i).getPrenom().replaceAll(" " ,"_") + "\t\t"+
                        clients.get(i).getEmail()+"\t\t" +
                        clients.get(i).getTel() + "\t\t" +
                        clients.get(i).getAddresse().replaceAll(" " ,"_") +"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}