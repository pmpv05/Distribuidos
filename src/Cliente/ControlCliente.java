package Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Clase que atiende el socket y las peticiones de usuario. 
 * Lo que llega por el socket lo muestra en el textArea del panel, lo que
 * escribe el usuario en el panel lo env�a por el socket.
 */
public class ControlCliente implements ActionListener, Runnable
{
    /** Para lectura de datos del socket */
    private DataInputStream dataInput;

    /** Para escritura de datos en el socket */
    private DataOutputStream dataOutput;

    /** Panel con los controles para el usuario */
    private PantallaCliente pantallaCliente;

    /**
     * Contruye una instancia de esta clase, lanzando un hilo para atender al
     * socket.
     * @param xSocket El socket
     * @param xPantallaCliente El panel del usuario
     */
    public ControlCliente(Socket xSocket, PantallaCliente xPantallaCliente)
    {
        this.pantallaCliente = xPantallaCliente;
        try
        {
            dataInput = new DataInputStream(xSocket.getInputStream());
            dataOutput = new DataOutputStream(xSocket.getOutputStream());

            Thread hilo = new Thread(this);
            hilo.start();
            
            this.pantallaCliente.setVisible(true);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Recoge el texto del panel y lo env�a por el socket.
     * El panel llamar� a este m�todo cuando el usuario escriba algo y 
     * pulse el bot�n de "enviar" o pulse "enter" sobre el textfield.
     */
    @Override
    public void actionPerformed(ActionEvent evento)
    {
        try
        {
            dataOutput.writeUTF(pantallaCliente.getTexto());
        } catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
    }

    /**
     * M�todo run para antender al socket. Todo lo que llega por el socket se
     * escribe en el panel.
     */
    public void run()
    {
        try
        {
            while (true)
            {
                String texto = dataInput.readUTF();
                pantallaCliente.addTexto(texto);
                pantallaCliente.addTexto("\n");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
