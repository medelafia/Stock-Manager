package org.example.services;

import org.example.entities.Fournisseur;
import org.example.repositories.RepoFournisseur;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ServicesFournisseur {
    private Scanner scanner ;
    private RepoFournisseur repoFournisseur ;
    public ServicesFournisseur( ) {
        this.scanner = new Scanner(System.in) ;
        this.repoFournisseur = new RepoFournisseur() ;
    }
    public void ajouterFournisseur() {
        System.out.flush();
        System.out.println("ajouter par");
        System.out.println("1.............Lignes des commandes");
        System.out.println("2..............fichier existant");
        switch (scanner.nextInt()) {
            case 1 :     scanner.nextLine();
                        System.out.println("entrer le nom de fournisseur");
                        String nom = scanner.nextLine() ;
                        System.out.println("entrer le tel de fournisseur");
                        String tel = scanner.nextLine() ;
                        System.out.println("entrer l'adresse de fournisseur");
                        String adresse = scanner.nextLine() ;
                        Fournisseur fournisseur = new Fournisseur(nom , tel , adresse ) ;
                        repoFournisseur.ajoute_Fournisseur(fournisseur);
                        break;
            case 2 :    System.out.println("entrer le chemin du fichier texte");
                        scanner.nextLine();
                        String filePath = scanner.nextLine();
                        FileInputStream file = null;
                        try {
                            file = new FileInputStream(filePath);
                            Scanner s = new Scanner(file);
                            System.out.println("Liste des client a partir du fichier 'Fournisseur.txt' \n");
                            System.out.print("IDF|\tNom\t|\tadresse\t|\tTel\n");
                            System.out.println("---------------------------------------------------------------------------");
                            while (s.hasNextLine() && s.hasNext() ) {
                                int sid = Integer.parseInt(s.next());
                                String snom = s.next();
                                String sadresse = s.next();
                                String stel = s.next();
                                System.out.print(" " + sid + "|\t");
                                System.out.print(snom + "\t|\t");
                                System.out.print(sadresse + "\t|\t");
                                System.out.println(stel +"\n");
                                repoFournisseur.ajoute_Fournisseur(new Fournisseur(snom.replaceAll("_" , " ") , stel ,
                                        sadresse.replaceAll("_" , " ")));
                            }
                        } catch (Exception e) {
                            System.out.println("there is a probleme" + e);
                        }
                break;
        }
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
                System.out.println("|\t" + list.get(i).getIdFournisseur() + "  |   "
                        + list.get(i).getNom()+ "\t|\t"
                        + list.get(i).getTel() + "\t|\t"
                        + list.get(i).getAddresse() + "\t|");
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
        scanner.nextLine() ;
        String nom = scanner.nextLine();
        System.out.println("entrer la nouveau tel de client");
        String tel = scanner.nextLine() ;
        System.out.println("entrer le nouveau addresse de client");
        String adresse = scanner.nextLine();
        Fournisseur fournisseur = new Fournisseur(id , nom , tel , adresse );
        repoFournisseur.modifier_Fournisseur(fournisseur);
    }
    public void exporterFournisseurs() {
        File file = new File("fournisseurs.txt") ;
        try (Writer writer = new FileWriter(file)){
            List<Fournisseur> fournisseurs = repoFournisseur.trouverToutesFournisseurs() ;
            for(int i=0 ; i<fournisseurs.size() ; i++) {
                writer.write(fournisseurs.get(i).getIdFournisseur()+"\t\t"+
                        fournisseurs.get(i).getNom().replaceAll(" " , "_")+"\t\t" +
                        fournisseurs.get(i).getTel() + "\t\t"+
                        fournisseurs.get(i).getAddresse().replaceAll(" " , "_")+"\n" ) ;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
