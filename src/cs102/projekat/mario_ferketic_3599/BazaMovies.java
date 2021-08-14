/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.mario_ferketic_3599;

import java.sql.Connection;
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
public class BazaMovies extends BazaAps {

    public static void addMovies(List<Movies> lista) {
        try {
            conn = DriverManager.getConnection(BazaAps.url, BazaAps.username, BazaAps.password);
            System.out.println("Konektovano...");
            PreparedStatement ps2 = conn.prepareStatement("DELETE FROM filmovi");
            ps2.executeUpdate();
            conn.createStatement();
            for (int i = 0; i < lista.size(); i++) {
                PreparedStatement st = conn.prepareStatement("INSERT INTO filmovi "
                        + "(ime, godina, ocena) VALUES(?,?,?)");

                st.setString(1, lista.get(i).getIme());
                st.setInt(2, lista.get(i).getGodina());
                st.setString(3, lista.get(i).getOcena());

                st.executeUpdate();
                conn.createStatement();
            }

            conn.close();
            // 
            System.out.println("Zavrsio filmove");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void kreni(String ime, ListView<String> lista) {
        lista.getItems().clear();
        try {
            conn = DriverManager.getConnection(BazaAps.url, BazaAps.username, BazaAps.password);
            System.out.println("Konektovano...");

            PreparedStatement st = conn.prepareStatement("SELECT * FROM filmovi WHERE ime LIKE '%" + ime + "%'");
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

            PreparedStatement st = conn.prepareStatement("SELECT * FROM filmovi ORDER BY " + staDaSort + " ASC");
            // st.setString(1, staDaSort);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                listaFX.getItems().add(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteMovie(Movies m, ListView<String> listaFX) {
        try {
            conn = DriverManager.getConnection(BazaAps.url, BazaAps.username, BazaAps.password);
            System.out.println("Konektovano...");
            PreparedStatement st = conn.prepareStatement("DELETE FROM filmovi "
                    + "WHERE (ime) LIKE(?)");
            st.setString(1, m.getIme());
            //  st.setInt(2, m.getGodina());
            // st.setString(3, m.getOcena());
            st.executeUpdate();
            conn.createStatement();

            conn.close();
            System.out.println("Uspesno obrisao " + m.getIme());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
