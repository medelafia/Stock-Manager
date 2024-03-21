package org.example.repositories;

import org.example.accessDB.AccessBD;
import org.example.entities.Commande;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RepoCommande {
    public void ajouteCommande(Commande commande) {

        Connection connection = AccessBD.connexionDB();
         if(connection!=null){
            String sql="INSERT INTO commande (etat , date , idCL)  VALUES(?,?,?)";
             PreparedStatement st = null;
             try {
                 st = connection.prepareStatement(sql);
                 st.setString(1 ,commande.getEtat());
                 st.setDate(2 , commande.getDate());
                 st.setInt(3 ,commande.getIdClient());
                 st.executeUpdate();
                 connection.close();
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             }
         }
         else{
             System.out.println("pas de connexion");
         }
    }

    public void modifierEtatCommande(int idCommande ,String etat){

        Connection cn =AccessBD.connexionDB();
        if(cn!=null){
         String sql = "UPDATE commande SET etat=? WHERE idCommande=?";
            PreparedStatement stUpdate = null;
            try {
                stUpdate = cn.prepareStatement(sql);
                stUpdate.setString(1 , etat);
                stUpdate.setInt(2 , idCommande);
                stUpdate.executeUpdate();
                cn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void supprimer_Commande( int idCommande){
        Connection cn =AccessBD.connexionDB();
        if(cn!=null){
            String sql="DELETE FROM commande WHERE idCommande=?";
            PreparedStatement stmt = null;
            try {
                stmt = cn.prepareStatement(sql);
                stmt.setInt(1 , idCommande);
                cn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public List<Commande> trouverToutesCommandes() {
        List<Commande> commandeList = new ArrayList<>() ;
        Connection connection = AccessBD.connexionDB() ;
        if(connection != null)  {
            String sql="SELECT * FROM COMMANDE";
            PreparedStatement stmt = null;
            try {
                stmt = connection.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    int idCommande = resultSet.getInt("idCommande");
                    String etat = resultSet.getString("etat") ;
                    Date date = resultSet.getDate("date") ;
                    int idClient = resultSet.getInt("idCl");
                    Commande commande = new Commande(idCommande , date , idClient , etat) ;
                    commandeList.add(commande) ;
                }
                connection.close();
                return commandeList ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null ;
    }
    public List<Integer> listProduitsCommande(int idCommande) {
        List<Integer> listIdsProduits = new ArrayList<>( );
        Connection connection = AccessBD.connexionDB() ;
        if(connection != null)  {
            String sql="SELECT pc.idP as id FROM COMMANDE c , PRODUIT p , ProduiCommande pc " +
                    "WHERE p.idProduit = pc.idP AND c.idCommande = pc.idC AND c.idCommande=?";
            PreparedStatement stmt = null;
            try {
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1 , idCommande);
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    listIdsProduits.add(id) ;
                }
                connection.close();
                return listIdsProduits ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null ;
    }
    public void ajouterProduitACommande(int idP , int qte , int idCommande ){
        Connection connection = AccessBD.connexionDB();
        if(connection!=null){
            String sql="INSERT INTO ProduiCommande (idC , qte , idP)  VALUES(?,?,?)";
            PreparedStatement st = null;
            try {
                st = connection.prepareStatement(sql);
                st.setInt(1 , idCommande);
                st.setInt(2 , qte);
                st.setInt(3 ,idP);
                st.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println("pas de connexion");
        }
    }
    public void supprimerProduitDeCommande(int idCommande , int idProduit) {
        Connection cn =AccessBD.connexionDB();
        if(cn!=null){
            String sql="DELETE FROM ProduiCommande WHERE idC=? and idP=? ";
            PreparedStatement stmt = null;
            try {
                stmt = cn.prepareStatement(sql);
                stmt.setInt(1 , idCommande);
                stmt.setInt(2 , idProduit);
                stmt.executeUpdate() ;
                cn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public int getQteProduit(int idProduit ,int  idCommande){
        Connection cn =AccessBD.connexionDB();
        int qte = 0 ;
        if(cn!=null){
            String sql="SELECT qte FROM ProduiCommande WHERE idC=? and idP=? ";
            PreparedStatement stmt = null;
            try {
                stmt = cn.prepareStatement(sql);
                stmt.setInt(1 , idCommande);
                stmt.setInt(2 , idProduit);
                ResultSet resultSet = stmt.executeQuery() ;
                while (resultSet.next()) {
                    qte = resultSet.getInt("qte") ;
                }
                cn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return qte ;
    }
}