package org.example.services;
import org.example.entities.Commande;
import org.example.entities.Produit;
import org.example.repositories.RepoCommande;
import org.example.repositories.RepoProduit;
import java.io.*;
import java.sql.Date;
import java.time.Clock;
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
                System.out.println("|\t" + list.get(i).getIdCommande() + "\t|\t"
                        + list.get(i).getDate() + "\t|\t"
                        + list.get(i).getIdClient() + "\t|\t"
                        + list.get(i).getEtat()+ "\t|");
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
        scanner.nextLine() ;
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
    public void exporterCommandes() {
        File file = new File("commandes.txt") ;
        try (Writer writer = new FileWriter(file)){
            List<Commande> commandes = repoCommande.trouverToutesCommandes() ;
            for(int i=0 ; i<commandes.size() ; i++) {
                writer.write(commandes.get(i).getIdCommande()+"\t\t\t"+
                        commandes.get(i).getDate()+"\t\t" +
                        commandes.get(i).getIdClient() + "\t\t"+
                        commandes.get(i).getEtat().replaceAll(" " , "_")+"\t\t");
                List<Integer> listProduits = repoCommande.listProduitsCommande(i) ;
                if( listProduits.size() == 0 ) writer.write("null");
                else {
                    for(int j = 0 ; j < listProduits.size() ; j++) {
                        writer.write(listProduits.get(j)+":"+repoCommande.getQteProduit(listProduits.get(j),i)+",");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void importerCommandes() {
        System.out.println("entrer le chemin du fichier texte");
        String filePath = scanner.nextLine();
        FileInputStream file = null;
        try {
            file = new FileInputStream(filePath);
            Scanner s = new Scanner(file);
            System.out.println("Liste des commandes a partir du fichier 'Commande.txt' \n");
            System.out.print("ID|\t etat\t|\t date\t|idclient\t|\tles ids des produits\n");
            System.out.println("---------------------------------------------------------------------------");
            while (s.hasNextLine() && s.hasNext()) {
                int sid = Integer.parseInt(s.next());
                Date sdate = Date.valueOf(s.next());
                int sidcl = s.nextInt();
                String setat = s.next();
                String idsProduits = s.next() ;
                System.out.print(" " + sid + "|\t");
                System.out.print(setat + "\t|\t");
                System.out.print(sdate + "\t|");
                System.out.print(sidcl +"\t|");
                System.out.print(idsProduits);
                repoCommande.ajouteCommande(new Commande(sdate,sidcl ,setat.replaceAll("_" ," ")));
                if(!idsProduits.equals("null")) {
                    String[] ids = idsProduits.split(",") ;
                    for(int i =0 ; i<ids.length ; i++) {
                        repoCommande.ajouterProduitACommande(Integer.parseInt(ids[i].split(":")[0]), Integer.parseInt(ids[i].split(":")[1]) , sid ) ;
                    }
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("le fichier n'existe pas");
        }
    }
}