package org.example.repositories;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import org.example.accessDB.AccessBD;
import org.example.entities.Fournisseur;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RepoFournisseur {
    public void ajoute_Fournisseur(Fournisseur fournisseur) {

        Connection connection = AccessBD.connexionDB();
        if(connection!=null){

            String sql="INSERT INTO FOURNISSEUR (nom,tel, adresse)  VALUES(?,?,?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1 , fournisseur.getNom() );
                preparedStatement.setString(2 , fournisseur.getTel());
                preparedStatement.setString(3 , fournisseur.getAddresse());
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

    }

    public void modifier_Fournisseur( Fournisseur fournisseur){
        Connection connection = AccessBD.connexionDB() ;
        if(connection!=null){
         String sql = "UPDATE fournisseur SET nom=?, tel=?,adresse=? WHERE idFournisseur=? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1 , fournisseur.getNom());
                preparedStatement.setString(2 , fournisseur.getTel());
                preparedStatement.setString(3 , fournisseur.getAddresse());
                preparedStatement.setInt(4 ,fournisseur.getIdFournisseur());
                preparedStatement.executeUpdate() ;
                connection.commit();
            } catch (SQLException e){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void supprimer_Fournisseur(int idFournisseur){
        Connection connection =AccessBD.connexionDB();
        if(connection!=null){
            String sql="DELETE FROM fournisseur WHERE idFournisseur=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idFournisseur);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public List<Fournisseur> trouverToutesFournisseurs() {
        List<Fournisseur> list = new ArrayList<>() ;
        Connection connection = AccessBD.connexionDB();
        if( connection != null ) {
            String query = "SELECT * FROM FOURNISSEUR";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("idFournisseur") ;
                    String nom = resultSet.getString("nom") ;
                    String adresse = resultSet.getString("adresse") ;
                    String tel = resultSet.getString("tel") ;
                    Fournisseur fournisseur = new Fournisseur(id , nom , tel , adresse ) ;
                    list.add(fournisseur) ;
                }
                connection.close();
                return list ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null ;
    }

}