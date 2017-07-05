/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDyGral;

import Cliente.Login;
import Servidor.ServidorChat;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import javax.swing.JOptionPane;
import org.postgresql.jdbc.PgConnection;

/**
 *
 * @author Michel
 */
public class Main {

    public static void main(String[] args) {

        try {
            String url = "localhost";
            String puerto = String.valueOf(5432);
            String bd = "chat";
            String user = "postgres";
            String pass = "7271";

            ConexionPostgres conexionPostgres = new ConexionPostgres(url, puerto, bd, user, pass);
            VarGlobal.setConPg(conexionPostgres.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión con PostgreSQL.");
        }

        try {
            String url = "localhost";
            int puerto = 27017;
            String bd = "chat";

            ConexionMongoDB conMongoDB = new ConexionMongoDB(url, puerto, bd);
            MongoClient mongoClient = conMongoDB.getMongoClient();
            VarGlobal.setMongoDatabase(conMongoDB.getDatabase());
            System.out.println("MongoDB");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión con MongoDB.");
        }

        VarGlobal.setDireccionSocket("localhost"); 
         VarGlobal.setPuertoSocket(5557);
        
        try {
            //Se inicia servidor
            new ServidorChat(VarGlobal.getPuertoSocket(), VarGlobal.getConPg(), VarGlobal.getMongoDatabase());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un error en iniciar el Servidor.");
        }
       
    }

}
