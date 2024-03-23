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
             try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                 preparedStatement.setString(1 ,commande.getEtat());
                 preparedStatement.setDate(2 , commande.getDate());
                 preparedStatement.setInt(3 ,commande.getIdClient());
                 preparedStatement.executeUpdate();
                 connection.commit();
             } catch (SQLException e) {
                 try {
                     connection.rollback();
                 } catch (SQLException ex) {
                     throw new RuntimeException(ex);
                 }
                 throw new RuntimeException(e);
             } finally {
                 try {
                     connection.close();
                 } catch (SQLException e) {
                     throw new RuntimeException(e);
                 }
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
            try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
                preparedStatement.setString(1 , etat);
                preparedStatement.setInt(2 , idCommande);
                preparedStatement.executeUpdate();
                cn.commit();
            } catch (SQLException e) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void supprimer_Commande( int idCommande){
        Connection cn =AccessBD.connexionDB();
        if(cn!=null){
            String sql="DELETE FROM commande WHERE idCommande=?";
            try (PreparedStatement stmt = cn.prepareStatement(sql)){
                stmt.setInt(1 , idCommande);
                stmt.executeUpdate() ;
                cn.commit();
            } catch (SQLException e) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1 , idCommande);
                preparedStatement.setInt(2 , qte);
                preparedStatement.setInt(3 ,idP);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
            try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
                preparedStatement.setInt(1 , idCommande);
                preparedStatement.setInt(2 , idProduit);
                preparedStatement.executeUpdate() ;
                cn.commit();
            } catch (SQLException e) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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