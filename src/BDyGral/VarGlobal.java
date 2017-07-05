/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDyGral;

import com.mongodb.client.MongoDatabase;
import java.sql.Connection;

/**
 *
 * @author Michel
 */
public class VarGlobal {
    
    public static Connection conPg;
    public static MongoDatabase mongoDatabase;
    public static String direccionSocket;
    public static int puertoSocket;
    public static String usuarioLogged;

    public static Connection getConPg() {
        return conPg;
    }

    public static void setConPg(Connection conPg) {
        VarGlobal.conPg = conPg;
    }

    public static MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public static void setMongoDatabase(MongoDatabase mongoDatabase) {
        VarGlobal.mongoDatabase = mongoDatabase;
    }

    public static String getDireccionSocket() {
        return direccionSocket;
    }

    public static void setDireccionSocket(String direccionSocket) {
        VarGlobal.direccionSocket = direccionSocket;
    }

    public static int getPuertoSocket() {
        return puertoSocket;
    }

    public static void setPuertoSocket(int puertoPg) {
        VarGlobal.puertoSocket = puertoPg;
    }

    public static String getUsuarioLogged() {
        return usuarioLogged;
    }

    public static void setUsuarioLogged(String usuarioLogged) {
        VarGlobal.usuarioLogged = usuarioLogged;
    }
    
}
