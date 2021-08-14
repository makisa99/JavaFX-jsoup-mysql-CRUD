/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.mario_ferketic_3599;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Mario
 */
public class CS102ProjekatMario_Ferketic_3599 extends Application {

    public static boolean firstTime = true;
    boolean filmoviSad = true;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        /////////////Launch treadove za skidanje//////////////////
        DownloadMovie dm = new DownloadMovie();
        Thread t1 = new Thread(dm);
        t1.start();
        t1.join();

        DownloadSerije ds = new DownloadSerije();
        Thread t2 = new Thread(ds);
        t2.start();
        ////////////////////////////////////////////////////////////
        HBox hb1 = new HBox(5);
        Label lb2 = new Label("Unesite ime filma:");
        TextField tf1 = new TextField();

        tf1.setPromptText("Unesite ime filma");
        Button srch = new Button("Search film");
        hb1.setAlignment(Pos.CENTER);
        hb1.getChildren().addAll(lb2, tf1, srch);

        HBox radioButtoni = new HBox(10);
        Label lb1 = new Label("Sortirajte po: ");
        RadioButton ime = new RadioButton("Imenu");
        RadioButton godini = new RadioButton("Godini");
        RadioButton oceni = new RadioButton("Oceni");
        Button flip = new Button("Flip");
        flip.setAlignment(Pos.BOTTOM_RIGHT);
        final ToggleGroup sortiranje = new ToggleGroup();
        ime.setToggleGroup(sortiranje);
        godini.setToggleGroup(sortiranje);
        oceni.setToggleGroup(sortiranje);
        radioButtoni.setAlignment(Pos.CENTER);
        radioButtoni.getChildren().addAll(lb1, ime, godini, oceni, flip);

        HBox hb2 = new HBox(5);
        Button btnSwitch = new Button("Ipak zelim serije");
        Label lb3 = new Label("Promenite misljenje:");

        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(lb3, btnSwitch);

        VBox root = new VBox(10);
        ListView<String> listaFX = new ListView<>();

        root.getChildren().addAll(hb1, radioButtoni, listaFX, hb2);
        //ODAVDE POCINJE KOD/////////////////////////////////////////
        srch.setOnAction(e -> {
            if (tf1.getText().length() > 0) {
                if (filmoviSad) {
                    BazaMovies.kreni(tf1.getText(), listaFX);
                } else {
                    BazaSerije.kreni(tf1.getText(), listaFX);
                }
            } else {
                AlertBox.display("Greska kod ucitavanja", "Morate prvo upisati ime videa");
                tf1.requestFocus();
            }
        }
        );

        ime.setOnAction(e -> {
            if (!firstTime) {
                if (filmoviSad) {
                    BazaMovies.sort("ime", listaFX);
                } else {
                    BazaSerije.sort("ime", listaFX);
                }
            } else {
                AlertBox.display("Greska kod sortiranja", "Prvo ucitajte podatke u listu");
            }
        }
        );
        godini.setOnAction(e -> {
            if (!firstTime) {
                if (filmoviSad) {
                    BazaMovies.sort("godina", listaFX);
                } else {
                    BazaSerije.sort("godina", listaFX);
                }
            } else {
                AlertBox.display("Greska kod sortiranja", "Prvo ucitajte podatke u listu");
            }
        }
        );
        oceni.setOnAction(e -> {
            if (!firstTime) {
                if (filmoviSad) {
                    BazaMovies.sort("ocena", listaFX);
                } else {
                    BazaSerije.sort("ocena", listaFX);
                }
            } else {
                AlertBox.display("Greska kod sortiranja", "Prvo ucitajte podatke u listu");
            }
        }
        );
        btnSwitch.setOnAction(e -> {
            if (AlertBox.display2("Konfirmacija", "Da li ste sigurni?")) {
                if (filmoviSad) {
                    firstTime = true;
                    listaFX.getItems().clear();
                    primaryStage.setTitle("Pretraga serija");
                    filmoviSad = false;
                    lb2.setText("Unesite ime serije:");
                    tf1.setPromptText("Unesite ime serije");
                    srch.setText("Search seriju");
                    btnSwitch.setText("Ipak zelim filmove");
                } else {
                    firstTime = true;
                    listaFX.getItems().clear();
                    primaryStage.setTitle("Pretraga filmova");
                    filmoviSad = true;
                    lb2.setText("Unesite ime filma:");
                    tf1.setPromptText("Unesite ime filma");
                    srch.setText("Search film");
                    btnSwitch.setText("Ipak zelim serije");
                }
            }
        }
        );

        flip.setOnAction(e -> {
            if (!firstTime) {
                List<String> listaFlip = listaFX.getItems();
                Collections.reverse(listaFlip);
            } else {
                AlertBox.display("Greska kod sortiranja", "Prvo ucitajte podatke u listu");
            }

        });

        Scene scene = new Scene(root, 800, 600);

        listaFX.setOnKeyPressed(e -> {
            try {
                if (e.getCode() == KeyCode.DELETE) {
                    String aaaa = listaFX.getSelectionModel().getSelectedItem();
                    BazaMovies.deleteMovie(new Movies(aaaa.substring(0, aaaa.length() - 9), Integer.parseInt(aaaa.substring(aaaa.length() - 8, aaaa.length() - 4)), aaaa.substring(aaaa.length() - 3)), listaFX);
                    listaFX.getItems().remove(aaaa);
                }
            } catch (NullPointerException ex) {
                System.out.println("Nema vise");
            }
        });

        primaryStage.setTitle("Pretraga filmova");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
