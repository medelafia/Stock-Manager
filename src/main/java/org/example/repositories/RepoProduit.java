package org.example.repositories;

import jdk.jfr.RecordingState;
import org.example.accessDB.AccessBD;
import org.example.entities.Fournisseur;
import org.example.entities.Produit;

import javax.naming.ldap.PagedResultsResponseControl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RepoProduit {
    public void ajouteProduit(Produit produit) {
        Connection connection = AccessBD.connexionDB();
        String sql="INSERT INTO produit (nom, description, prix, qte , idF)  VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,produit.getNom());
            preparedStatement.setString(2, produit.getDescription());
            preparedStatement.setFloat(3,produit.getPrix());
            preparedStatement.setInt(4,produit.getQte());
            preparedStatement.setInt(5, produit.getIdFournisseur());
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
    public void supprimerProduit(int idProduit) {
        Connection connection = AccessBD.connexionDB() ;
        if( connection != null ) {
            String sql = "DELETE FROM PRODUIT WHERE idProduit=?" ;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1 , idProduit);
                preparedStatement.executeUpdate() ;
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
    }
    public List<Produit> trouverToutesProduits() {
        List<Produit> list = new ArrayList<>() ;
        Connection connection = AccessBD.connexionDB();
        if( connection != null ) {
            String query = "SELECT * FROM PRODUIT";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idProduit = resultSet.getInt("idProduit");
                    String nom = resultSet.getString("nom") ;
                    String description = resultSet.getString("description") ;
                    float prix = resultSet.getFloat("prix") ;
                    int qte = resultSet.getInt("qte");
                    int idFournisseur = resultSet.getInt("idF") ;
                    Produit produit = new Produit(idProduit , nom , description , prix , qte , idFournisseur ) ;
                    list.add(produit) ;
                }
                connection.close();
                return list ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null ;
    }
    public void modifierProduit(Produit produit) {
        Connection connection = AccessBD.connexionDB() ;
        if(connection!=null){
            String sql = "UPDATE PRODUIT SET nom=? , description=? , prix=? , qte=?,  idF=? WHERE idProduit=? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1 , produit.getNom());
                preparedStatement.setString(2 , produit.getDescription());
                preparedStatement.setFloat(3 , produit.getPrix());
                preparedStatement.setInt(4 , produit.getQte());
                preparedStatement.setInt(5 , produit.getIdFournisseur());
                preparedStatement.setInt(6 , produit.getIdP() );
                preparedStatement.executeUpdate() ;
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
    }

    public Produit getProduitAvecId(int idProduit) {
        Connection connection = AccessBD.connexionDB() ;
        Produit produit = null ;
        if( connection != null) {
            String sql = "SELECT * FROM PRODUIT WHERE idProduit=?" ;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
                preparedStatement.setInt(1 , idProduit );
                ResultSet resultSet = preparedStatement.executeQuery() ;
                while (resultSet.next()) {
                    String nom = resultSet.getString("nom") ;
                    String description = resultSet.getString("description");
                    float prix = resultSet.getFloat("prix") ;
                    int qte = resultSet.getInt("qte") ;
                    int idF = resultSet.getInt("idF") ;
                    produit = new Produit(idProduit , nom , description , prix , qte , idF) ;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return produit ;
    }
}
   
            