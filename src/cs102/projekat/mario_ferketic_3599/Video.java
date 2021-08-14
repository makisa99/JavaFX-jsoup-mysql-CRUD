/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.mario_ferketic_3599;

/**
 *
 * @author Mario
 */
public class Video {
    protected String ime;
    protected int godina;
    protected String ocena;

    public Video(String ime, int godina, String ocena) {
        this.ime = ime;
        this.godina = godina;
        this.ocena = ocena;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public String getOcena() {
        return ocena;
    }

    public void setOcena(String ocena) {
        this.ocena = ocena;
    }

}
