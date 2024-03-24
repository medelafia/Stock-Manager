package org.example.services;

import org.example.entities.Produit;
import org.example.repositories.RepoProduit;
import java.io.*;
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
        System.out.flush();
        System.out.println("ajouter par");
        System.out.println("1.............Lignes des commandes");
        System.out.println("2..............fichier existant");
        switch (scanner.nextInt()) {
            case 1 :  scanner.nextLine();
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
                break;
            case 2 :    System.out.println("entrer le chemin du fichier texte");
                        scanner.nextLine();
                        String filePath = scanner.nextLine();
                        FileInputStream file = null;
                        try {
                            file = new FileInputStream(filePath);
                            try (Scanner s = new Scanner(file)) {
                                System.out.println("Liste des produit a partir du fichier 'Produit.txt' \n");
                                System.out.print("ID|\tNom\t|\tdescription\t|\tprix\t|\tidFournisseur\t\n");
                                System.out.println("---------------------------------------------------------------------------");
                                while (s.hasNextLine() && s.hasNext()) {
                                    int sid = s.nextInt();
                                    String snom = s.next();
                                    String sdescription = s.next();
                                    float sprix = Float.parseFloat(s.next());
                                    int sQte = Integer.parseInt(s.next());
                                    int sidF = Integer.parseInt(s.next());
                                    System.out.print(" " + sid + "|\t");
                                    System.out.print(snom + "\t|\t");
                                    System.out.print(sdescription + "\t|");
                                    System.out.print(sprix + "\t|\t");
                                    System.out.println(sidF);
                                    repoProduit.ajouteProduit(new Produit(snom.replaceAll("_" , " "),
                                            sdescription.replaceAll("_" , " "), sprix, sQte, sidF));
                                }
                                System.out.println(" insert avec succes");
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("the file not exist");
                        }
                break ;
        }
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
                System.out.println("|\t" + produits.get(i).getIdP() + "\t|\t"
                        + produits.get(i).getNom() + "\t|\t"
                        + produits.get(i).getDescription() + "\t|\t"
                        + produits.get(i).getPrix() + "\t|\t"
                        + produits.get(i).getQte() + "\t|\t"
                        + produits.get(i).getIdFournisseur() + "\t|\t"
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
        scanner.nextLine() ;
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
    public void exporterProduits() {
        File file = new File("produits.txt") ;
        try (Writer writer = new FileWriter(file)){
            List<Produit> produits = repoProduit.trouverToutesProduits();
            for(int i=0 ; i<produits.size() ; i++) {
                writer.write(produits.get(i).getIdP()+"\t\t"+
                        produits.get(i).getNom().replaceAll(" " , "_")+"\t\t" +
                        produits.get(i).getDescription().replaceAll(" " , "_") + "\t\t"+
                        produits.get(i).getPrix()+"\t\t" +
                        produits.get(i).getQte() + "\t\t" +
                        produits.get(i).getIdFournisseur() +"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
