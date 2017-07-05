/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDyGral;

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

    public static Connection conPg = null;
    public static MongoDatabase mongoDatabase = null;
    public static String direccionSocket;
    public static int puertoPg;

    public static void main(String[] args) {

        try {
            String url = "localhost";
            String puerto = String.valueOf(5432);
            String bd = "chat";
            String user = "postgres";
            String pass = "7271";

            ConexionPostgres conexionPostgres = new ConexionPostgres(url, puerto, bd, user, pass);
            conPg = conexionPostgres.getConnection();
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
            mongoDatabase = conMongoDB.getDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexión con MongoDB.");
        }

        direccionSocket = "localhost"; 
        puertoPg = 5557;
        
        try {
            //Se inicia servidor
            new ServidorChat(puertoPg, conPg, mongoDatabase);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un error en iniciar el Servidor.");
        }
    }

}
