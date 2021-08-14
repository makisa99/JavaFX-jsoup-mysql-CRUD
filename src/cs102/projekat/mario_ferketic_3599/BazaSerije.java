/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.mario_ferketic_3599;

import static cs102.projekat.mario_ferketic_3599.BazaAps.conn;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.ListView;

/**
 *
 * @author Mario
 */
public class BazaSerije {

    public static void addSerije(List<Serije> lista) {
        try {
            conn = DriverManager.getConnection(BazaAps.url, BazaAps.username, BazaAps.password);
            System.out.println("Konektovano...");
            PreparedStatement ps2 = conn.prepareStatement("DELETE FROM serije");
            ps2.executeUpdate();
            conn.createStatement();
            for (int i = 0; i < lista.size(); i++) {
                PreparedStatement st = conn.prepareStatement("INSERT INTO serije "
                        + "(ime, godina, ocena) VALUES(?,?,?)");

                st.setString(1, lista.get(i).getIme());
                st.setInt(2, lista.get(i).getGodina());
                st.setString(3, lista.get(i).getOcena());

                st.executeUpdate();
                conn.createStatement();
            }

            conn.close();
           // CS102ProjekatMario_Ferketic_3599.firstTime = false;
            System.out.println("Zavrsio serije");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void kreni(String ime, ListView<String> lista) {
        lista.getItems().clear();
        try {
             conn = DriverManager.getConnection(BazaAps.url, BazaAps.username, BazaAps.password);
            System.out.println("Konektovano...");

            PreparedStatement st = conn.prepareStatement("SELECT * FROM serije WHERE ime LIKE '%" + ime + "%'");
            ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    lista.getItems().add(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                }

            conn.close();
            CS102ProjekatMario_Ferketic_3599.firstTime = false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void sort(String staDaSort, ListView<String> listaFX) {
        listaFX.getItems().clear();
        try {
            conn = DriverManager.getConnection(BazaAps.url, BazaAps.username, BazaAps.password);
            System.out.println("Konektovano...");

            PreparedStatement st = conn.prepareStatement("SELECT * FROM serije ORDER BY " + staDaSort + " ASC");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                listaFX.getItems().add(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
