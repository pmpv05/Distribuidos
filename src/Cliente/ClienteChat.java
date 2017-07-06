package Cliente;

import BDyGral.ConexionMongoDB;
import BDyGral.ConexionPostgres;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.net.Socket;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Clase con el main de un cliente del chat. Establece la conexion y crea la
 * ventana y la clase de control.
 */
public class ClienteChat {
    /**
     * Socket con el servidor del chat
     */
    private Socket socket;

    /**
     * PantallaCliente con la ventana del cliente
     */
    private PantallaCliente pantallaCliente;

    public static Connection conPg;
    public static MongoDatabase mongoDatabase;
    public static String direccionSocket;
    public static int puertoSocket;
    public static String usuarioLogged;
    public static ConexionPostgres conexionPostgres;
public static MongoClient mongoClient;
public static ConexionMongoDB conMongoDB;
    /**
     * Crea la ventana, establece la conexi�n e instancia al controlador.
     */
    public ClienteChat(String xDireccionSocket, int xPuerto) {
        try {
            String url = "localhost";
            String puerto = String.valueOf(5432);
            String bd = "chat";
            String user = "postgres";
            String pass = "7271";

            conexionPostgres = new ConexionPostgres(url, puerto, bd, user, pass);
            conPg = conexionPostgres.getConnection();
            
            int puertoMongo = 27017;
            String bdMongo = "chat";

            conMongoDB = new ConexionMongoDB(url, puertoMongo, bdMongo);
            mongoClient = conMongoDB.getMongoClient();
            mongoDatabase = mongoClient.getDatabase(bdMongo);
            
            this.socket = new Socket(xDireccionSocket, xPuerto);
            this.pantallaCliente = new PantallaCliente();
            ControlCliente control = new ControlCliente(socket, pantallaCliente);
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
}
