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
public class TopPaneSearchBook {

    private static String searchString;
    
    public TopPaneSearchBook() {

    }
    
    public static String display(){
        Stage primaryStage = new Stage();
        
        Label label = new Label("Search your book");
        TextField searchInput = new TextField();
        Button button = new Button("Search");
        searchString = null;
        button.setOnAction(e -> {
            searchString = searchInput.getText();
            primaryStage.close();
        });
        
        button.setLayoutX(195);
        button.setLayoutY(220);
        searchInput.setLayoutX(50);
        searchInput.setLayoutY(60);
        label.setLayoutX(50);
        label.setLayoutY(30);
        
        Pane pane = new Pane();
        pane.getChildren().addAll(label, searchInput, button);
        Scene scene = new Scene(pane, 250, 250);
        
        primaryStage.setScene(scene);
        primaryStage.showAndWait();  
        
        return searchString;
    }
}
