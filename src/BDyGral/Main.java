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
import org.postgresql.jdbc.PgConnection;

/**
 *
 * @author Michel
 */
public class Main {
    
    
    public static void main(String[] args){
        ConexionMongoDB conMongoDB = new ConexionMongoDB();
        MongoClient mongoClient = conMongoDB.getMongoClient();
        MongoDatabase mongoDatabase = conMongoDB.getDatabase();
        
        ConexionPostgres conexionPostgres = new ConexionPostgres();
        Connection conPg = conexionPostgres.getConnection();
        
        int puertoPg = 5557;
        //Se inicia servidor
        new ServidorChat(puertoPg, conPg, mongoDatabase);
        
        
    }
    
}
