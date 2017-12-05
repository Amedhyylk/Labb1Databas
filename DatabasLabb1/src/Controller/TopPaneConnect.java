/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed Heidari 2
 */
public class TopPaneConnect {

    private static String[] ConnectString = new String[2];

    public TopPaneConnect() {

    }

    public static String[] display() {
        Stage primaryStage = new Stage();

        Label label_IP = new Label("Enter IP");
        Label label_port = new Label("Enter port");
        TextField IP_input = new TextField();
        TextField port_input = new TextField();
        Button button = new Button("Confirm");
        ConnectString[0] = null;
        ConnectString[1] = null;
        button.setOnAction(e -> {
            ConnectString[0] = IP_input.getText();
            ConnectString[1] = port_input.getText();            
            primaryStage.close();
        });

        button.setLayoutX(195);
        button.setLayoutY(220);
        IP_input.setLayoutX(50);
        IP_input.setLayoutY(60);
        label_IP.setLayoutX(50);
        label_IP.setLayoutY(30);
        label_port.setLayoutX(50);
        label_port.setLayoutY(90);
        port_input.setLayoutX(50);
        port_input.setLayoutY(110);

        

        Pane pane = new Pane();
        pane.getChildren().addAll(label_IP, IP_input, port_input, button, label_port);
        Scene scene = new Scene(pane, 250, 250);

        primaryStage.setScene(scene);
        primaryStage.showAndWait();

        return ConnectString;
    }
}
