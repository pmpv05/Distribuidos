/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDyGral;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class UsuariosControl {

    private static String url = "localhost";
    private static String puerto = String.valueOf(5432);
    private static String bd = "chat";
    private static String user = "postgres";
    private static String pass = "7271";

    private static ConexionPostgres conexionPostgres = new ConexionPostgres(url, puerto, bd, user, pass);
    private static Connection con = conexionPostgres.getConnection();

    public static int login(String xUsuario, String xPass) {
        int res = 0;

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM usuarios WHERE usuario = '%s';", xUsuario));

            while (rs.next()) {
                res = 1;
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (res == 1) {
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(String.format("SELECT * FROM usuarios WHERE usuario = '%s' AND pass = '%s';", xUsuario, xPass));

                while (rs.next()) {
                    res = 2;
                }
                rs.close();
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (res == 0) {
            JOptionPane.showMessageDialog(null, "El usuario ingresado no existe.");
        } else if (res == 1) {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son correctos.");
        }

        return res;
    }

    public static int registroUsuario(String xUsuario, String xPass, String xNombre) {
        int res = 0;

        try {
            Statement st = con.createStatement();

            String sql = String.format("INSERT INTO usuarios (usuario, pass, nombre) VALUES ('%s','%s','%s');", xUsuario, xPass, xNombre);

            res = st.executeUpdate(sql);
            st.close();
            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar el usuario.\n"+e);
        }

        return res;
    }

}
