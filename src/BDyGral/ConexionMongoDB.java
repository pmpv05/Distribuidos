/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDyGral;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class ConexionMongoDB {

    MongoClient mongoClient;
    MongoDatabase database;

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public ConexionMongoDB(String xURL, int xPuerto, String xBD) {
        try {
 
            // To connect to mongodb server
            this.mongoClient = new MongoClient(xURL, xPuerto); 
            // Now connect to your databases
            this.database = mongoClient.getDatabase(xBD);
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
