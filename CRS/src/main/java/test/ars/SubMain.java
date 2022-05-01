
package test.ars;
import javafx.application.Application;


import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;


import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.*;

public class SubMain extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        Image image = new Image(new FileInputStream("src/main/java/test/ars/image.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(150);
        imageView.setY(250);
        Label ltxt = new Label("AIRLINE RESERVATION SYSTEM");
        Button Welcome = new Button("CLICK TO CONTINUE");
        Welcome.setOnAction(new Continue());
         VBox root = new VBox();
         root.getChildren().addAll(ltxt,Welcome, imageView);

        stage.setTitle("ARS");
        stage.setScene(new Scene(root,350, 250, Color.ALICEBLUE));
        stage.show();
    }





    public static void main(String[] args) throws Exception {
        Application.launch(args);
}
}


