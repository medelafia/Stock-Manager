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
            PreparedStatement st = null;
            try {
                st = connection.prepareStatement(sql);
                st.setString(1 , client.getNom());
                st.setString(2 , client.getPrenom());
                st.setString(3 , client.getEmail());
                st.setString(4 , client.getTel());
                st.setString(5 , client.getAddresse());
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

    public void modifier_client(Client client){
        Connection cn =AccessBD.connexionDB();
        if(cn!=null){
            String sql = "UPDATE client SET nom='"+client.getNom()+"', prenom='"+client.getPrenom()+"',email='"+client.getEmail()+"', tel='"+client.getTel()+"' ,addresse='"+client.getAddresse()+"'WHERE idClient='"+ client.getIdClient()+"' ";
            Statement stUpdate = null;
            try {
                stUpdate = cn.createStatement();
                stUpdate.executeUpdate(sql);
                cn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println("pas de connexion");
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
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println("pas de connexion");
        }
    }
    public List<Client> trouverToutesClients() {
        List<Client> clients = new ArrayList<>() ;
        Connection connection = AccessBD.connexionDB();
        if(connection!=null){
            String sql="SELECT * FROM CLIENT";
            PreparedStatement stmt = null;
            try {
                stmt = connection.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery();
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
                connection.close();
                return clients ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null ;
    }
}
