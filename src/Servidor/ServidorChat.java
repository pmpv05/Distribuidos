package Servidor;

import BDyGral.ConexionMongoDB;
import BDyGral.ConexionPostgres;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;

/**
 * Este es el Servidor del chat. Lo que hace es aceptar conexiones de clientes,
 * crea un hilo para atenderlos, y espera la siguiente conexion.
 *
 */
public class ServidorChat {

    /**
     * Lista en la que se guaradara toda la conversacion
     */
    private DefaultListModel charla = new DefaultListModel();
    private ConexionPostgres conexionPg;
    private ConexionMongoDB conexionMongo;

    /**
     * Se mete en un bucle infinito para ateder clientes, lanzando un hilo para
     * cada uno de ellos.
     */
    public ServidorChat(int xPuerto, ConexionPostgres xConexionPg, ConexionMongoDB xConexionMongo) {
        try {
            ServerSocket socketServidor = new ServerSocket(xPuerto);
            this.conexionPg = xConexionPg;
            this.conexionMongo = xConexionMongo;

            while (true) {
                Socket cliente = socketServidor.accept();
                Runnable nuevoCliente = new HiloDeCliente(charla, cliente);
                Thread hilo = new Thread(nuevoCliente);
                hilo.start();
                System.out.println("> Se inicia hilo con nuevo cliente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
