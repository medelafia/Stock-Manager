package org.example.repositories;

import org.example.accessDB.AccessBD;
import org.example.entities.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RepoClient{
    public void ajoute_client(Client client) {
        Connection connection = AccessBD.connexionDB();
        if(connection!=null){
            String sql="INSERT INTO client (nom, prenom, email, tel, adresse)  VALUES(?,?,?,?,?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1 , client.getNom());
                preparedStatement.setString(2 , client.getPrenom());
                preparedStatement.setString(3 , client.getEmail());
                preparedStatement.setString(4 , client.getTel());
                preparedStatement.setString(5 , client.getAddresse());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void modifier_client(Client client){
        Connection cn =AccessBD.connexionDB();
        if(cn!=null){
            String sql = "UPDATE client SET nom='"+client.getNom()+"', prenom='"+client.getPrenom()+"',email='"+client.getEmail()+"', tel='"+client.getTel()+"' ,addresse='"+client.getAddresse()+"'WHERE idClient='"+ client.getIdClient()+"' ";
            Statement stUpdate = null;
            try {
                stUpdate = cn.createStatement();
                stUpdate.executeUpdate(sql);
                cn.commit();
            } catch (SQLException e) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void supprimer_client(int idClient){
        Connection connection =AccessBD.connexionDB();
        if(connection!=null){
            String sql="DELETE FROM client WHERE idClient=? ";
            PreparedStatement stmt = null;
            try {
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1 , idClient);
                stmt.executeUpdate();
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
    public List<Client> trouverToutesClients() {
        List<Client> clients = new ArrayList<>() ;
        Connection connection = AccessBD.connexionDB();
        if(connection!=null){
            String sql="SELECT * FROM CLIENT";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("idClient");
                    String nom = resultSet.getString("nom") ;
                    String prenom = resultSet.getString("prenom") ;
                    String email = resultSet.getString("email") ;
                    String tel = resultSet.getString("tel");
                    String adresse = resultSet.getString("adresse") ;
                    Client client = new Client(id , nom , prenom , email , tel , adresse );
                    clients.add(client) ;
                }
                return clients ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null ;
    }
}
