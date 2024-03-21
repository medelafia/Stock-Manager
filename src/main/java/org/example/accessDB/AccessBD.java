
package org.example.accessDB;
import java.sql.*;


public class AccessBD {
    private static String PROTOCOLE = "jdbc:mysql" ;
    private static String HOST_NAME = "localhost" ;
    private static int PORT = 3306 ;
    private static String USERNAME = "root";
    private static String PASSWORD = "";
    public static Connection connexionDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(String.format("%s://%s:%d/GESTIONSTOCK?useSSL=false",PROTOCOLE,HOST_NAME,PORT),USERNAME, PASSWORD);
            return conn;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    private static void creationDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(PROTOCOLE + "://"+HOST_NAME+":"+PORT+"/?useSSL=false",USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE GESTIONSTOCK");
            System.out.println("Base de données crée avec succés...");
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

        
    private static void creatTableProduit(){
        Connection conn = AccessBD.connexionDB();
        if(conn != null){
            try{
                Statement stmt = conn.createStatement();
                String sql ="CREATE TABLE IF NOT EXISTS PRODUIT "+
                        "(idProduit INTEGER NOT NULL AUTO_INCREMENT ,"+
                        "nom VARCHAR (50),"+
                        "description VARCHAR(100) ,"+
                        "prix FLOAT   ,"+
                        "qte INTEGER ,"+
                        "idF INTEGER , " +
                        "PRIMARY KEY (idProduit),"+
                        "FOREIGN KEY (idF)REFERENCES FOURNISSEUR(idFournisseur))";
                stmt.executeUpdate(sql);
                System.out.println("Table de produit crée avec succés...");
                conn.close();
            }
            catch(SQLException e){
                System.out.println( e);
            }
        }
    }
     private static void createTableFournisseur(){
        Connection conn = AccessBD.connexionDB();
        if(conn != null) {
            try{
                Statement stmt = conn.createStatement();
                String sql ="CREATE TABLE IF NOT EXISTS FOURNISSEUR "+
                        "(idFournisseur INTEGER  NOT NULL AUTO_INCREMENT,"+
                        "nom VARCHAR (50),"+
                        "adresse VARCHAR(100),"+
                        "tel VARCHAR(14) NOT NULL ," +
                        "PRIMARY KEY (idFournisseur))";

                stmt.executeUpdate(sql);
                System.out.println("Table de Fournisseur crée avec succés...");
                conn.close();
            }
            catch(SQLException e){
                System.out.println( e);
            }
        }
    }
     private static void createTableClient(){
        Connection conn = AccessBD.connexionDB();
        if(conn != null) {
            try{
                Statement stmt = conn.createStatement();

                String sql ="CREATE TABLE IF NOT EXISTS CLIENT "+
                        "(idClient INTEGER NOT NULL AUTO_INCREMENT ,"+
                        "nom VARCHAR (50),"+
                        "prenom VARCHAR (50),"+
                        "email VARCHAR (50),"+
                        "tel VARCHAR(14) , " +
                        "adresse VARCHAR(50),"+
                        "PRIMARY KEY (idClient))";
                stmt.executeUpdate(sql);
                System.out.println("Table de Client crée avec succés...");
                conn.close();
            }
            catch(SQLException e){
                System.out.println( e);
            }
        }
    }
     private static void createTableCommande(){
        Connection conn = AccessBD.connexionDB();
        if(conn != null) {
            try{
                Statement stmt = conn.createStatement();

                String sql ="CREATE TABLE IF NOT EXISTS COMMANDE "+
                        "(idCommande INTEGER NOT NULL AUTO_INCREMENT ,"+
                        "etat VARCHAR (25),"+
                        "date DATE,"+
                        "idCl INTEGER ,"+
                        "PRIMARY KEY (idCommande)," +
                        "FOREIGN KEY (idCl)REFERENCES CLIENT(idClient))";
                stmt.executeUpdate(sql);
                System.out.println("Table de Commande crée avec succés...");
                conn.close();
            }
            catch(SQLException e){
                System.out.println( e);
            }
        }
    }
    private static void createTableProduiCommande(){
        Connection conn = AccessBD.connexionDB();
        if( conn != null )  {
            try{
                Statement stmt = conn.createStatement();

                String sql ="CREATE TABLE IF NOT EXISTS ProduiCommande "+
                        "(idC INTEGER NOT NULL ,"+
                        "idP INTEGER NOT NULL ,"+
                        "qte INTEGER NOT NULL ,"+
                        " FOREIGN KEY (idC) REFERENCES COMMANDE(idCommande),"+
                        " FOREIGN KEY (idP) REFERENCES PRODUIT(idProduit) )";
                stmt.executeUpdate(sql);
                System.out.println("Table de PrduitCommande crée avec succés...");
                conn.close();
            }
            catch(SQLException e){
                System.out.println( e);
            }
        }
    }
    public static void main(String[] args) {
        creationDB();
        createTableClient();
        createTableFournisseur();
        creatTableProduit();
        createTableCommande();
        createTableProduiCommande();
    }
}