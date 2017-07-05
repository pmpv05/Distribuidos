package Servidor;

import BDyGral.ConexionMongoDB;
import BDyGral.ConexionPostgres;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;

/**
 * Este es el Servidor del chat.
 * Lo que hace es aceptar conexiones de clientes, crea un hilo para atenderlos, y espera la
 * siguiente conexion.
 *
 */
public class ServidorChat
{
    private int puerto = 5557;
    /** Lista en la que se guaradara toda la conversacion */
    private DefaultListModel charla = new DefaultListModel();
    private ConexionPostgres conexionPg;
    private ConexionMongoDB conexionMongo;
    /**
     * Se mete en un bucle infinito para ateder clientes, lanzando un hilo
     * para cada uno de ellos.
     */
    public ServidorChat()
    {
        try
        {
            ServerSocket socketServidor = new ServerSocket(puerto);
            this.conexionPg = new ConexionPostgres();
            this.conexionMongo = new ConexionMongoDB();
            
            while (true)
            {
                Socket cliente = socketServidor.accept();
                Runnable nuevoCliente = new HiloDeCliente(charla, cliente);
                Thread hilo = new Thread(nuevoCliente);
                hilo.start();
                System.out.println("> Se inicia hilo con nuevo cliente");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        new ServidorChat();
        System.out.println(">>> Servidor iniciado");
         
    }
}
