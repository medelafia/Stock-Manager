package org.example.services;

import org.example.accessDB.AccessBD;

import java.io.BufferedReader;
import java.sql.* ;
import java.io.* ;


public class ServiceLogin {

    private static BufferedReader keyboard = new BufferedReader( new InputStreamReader( System.in ) );
    
    public void Login (){
        Connection connection = AccessBD.connexionDB();
        try {
           //System.out.println( "Bienvenue a l'espace d'Admin");
           while (true) {
               System.out.print(" saisie votre login: ");
               String email = keyboard.readLine();
               System.out.print(" saisie votre Password: ");
               String password = keyboard.readLine();
               String sql = "SELECT * FROM users WHERE login=? AND password=?";
               PreparedStatement stmt = connection.prepareStatement(sql);
               stmt.setString(1, email);
               stmt.setString(2, password);
               ResultSet res = stmt.executeQuery();
               if (res.next()) {
                   break;
               } else {
                   System.out.println("Invalide email ou password");
               }
           }
       } catch (Exception e) {
         System.out.println(e);
        }finally {
            try {
                connection.close() ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
   }
         
}
