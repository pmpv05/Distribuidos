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

    static Statement st = null;
    static ResultSet rs = null;

    public static int login(String xUsuario, String xPass, Connection xConn) {
        int res = 0;

        try {
            st = xConn.createStatement();
            rs = st.executeQuery(String.format("SELECT * FROM usuarios WHERE user = {0}", xUsuario));

            while (rs.next()) {
                res = 1;
            }
            rs.close();
            st.close();
            xConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (res == 1) {
            try {
                st = xConn.createStatement();
                rs = st.executeQuery(String.format("SELECT * FROM usuarios WHERE user = '{0}' AND pass = '{1}'", xUsuario, xPass));

                while (rs.next()) {
                    res = 2; 
                }
                rs.close();
                st.close();
                xConn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (res == 0) {
            JOptionPane.showMessageDialog(null, "El usuario ingresado no existe. Puede registrar un usuario accediendo al menú principal.");
        } else if (res == 1) {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son correctos.");
        }

        return res;
    }

    public static int registroUsuario(String xUsuario, String xPass, String xNombre, Connection xConn) {
        int res = 0;

        try {
            st = xConn.createStatement();

            String sql = String.format("INSERT INTO usuarios (user, pass, name) VALUES ('{0}','{1}','{2}')", xUsuario, xPass, xNombre);

            res = st.executeUpdate(sql);
            st.close();
            xConn.commit();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return res;
    }

}
