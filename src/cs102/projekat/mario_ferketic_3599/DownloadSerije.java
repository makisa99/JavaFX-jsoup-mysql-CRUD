/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.mario_ferketic_3599;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Mario
 */
public class DownloadSerije implements Runnable{

    @Override
     public void run() {
        List<Serije> lista = new ArrayList<>();
        try {
            System.out.println("Krece2");
            Document doc = Jsoup.connect("https://www.imdb.com/chart/toptv/").get();
            System.out.println(doc.baseUri());
            Elements stvar = doc.select(".titleColumn > a");
            Elements godina = doc.select(".titleColumn .secondaryInfo");
            Elements ocena = doc.select(".ratingColumn > strong");
            System.out.println(stvar.size());
            for (int i = 0; i < stvar.size(); i++) {
                Serije s = new Serije(stvar.get(i).text(),Integer.parseInt(godina.get(i).text().substring(1, godina.get(i).text().length() - 1)),ocena.get(i).text());
                lista.add(s);
                System.out.println("Dodao" + i);
            }
              BazaSerije.addSerije(lista);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }     
}
