/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Book;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Model.Author;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ahmed Heidari 2
 */
public class AddBook {

    private static Book book;
    private static int i = 1;

    public static Book display() {

        Stage primaryStage = new Stage();
        primaryStage.setResizable(false);
        //****ADDBOK****
        Button saveButtonAddBook = new Button("Save");

        //another author
        Button anotherAuthor = new Button("+");

        //scene label
        Label labelAddTitle = new Label("BOOK TITLE");
        Label labelAddAuthors = new Label("AUTHORS");
        Label labelAddISBN = new Label("ISBN");
        Label labelAddEdition = new Label("EDITION");
        Label labelAddPrice = new Label("PRICE");
        Label labelAddGenre = new Label("GENRE");

        Label labelGrade = new Label("Grade");
        // textfield input
        TextField titleInput = new TextField();
        TextField ISBNInput = new TextField();
        TextField editionInput = new TextField();
        TextField priceInput = new TextField();
        TextField gradeInput = new TextField();

        ArrayList<TextField> authorsInput = new ArrayList<>();

        ObservableList<String> optionsGenre = FXCollections.observableArrayList(
                "Romance",
                "Comedy",
                "Horror",
                "Thriller",
                "Adventure",
                "Children"
        );

        ObservableList<Integer> optionsGrade = FXCollections.observableArrayList(
                1,
                2,
                3,
                4,
                5
        );

        ComboBox genreList = new ComboBox(optionsGenre);
        ComboBox gradeList = new ComboBox(optionsGrade);
        genreList.setValue("Romance");
        GridPane layoutAddBook = new GridPane();
        authorsInput.add(new TextField());
        layoutAddBook.setPadding(new Insets(5, 5, 5, 5));
        layoutAddBook.setHgap(10);
        layoutAddBook.setVgap(10);
        layoutAddBook.getChildren().addAll(labelAddTitle, titleInput, labelAddAuthors, labelAddISBN, ISBNInput, labelAddEdition, editionInput, labelAddPrice, priceInput, saveButtonAddBook,
                backButton(primaryStage), genreList, labelAddGenre, anotherAuthor, authorsInput.get(0), labelGrade, gradeList);
        Scene scene = new Scene(layoutAddBook, 350, 400);

        i = 1;

        anotherAuthor.setOnAction(e -> {
            if (authorsInput.size() < 4) {
                authorsInput.add(new TextField());
                layoutAddBook.getChildren().addAll(authorsInput.get(i));
                GridPane.setConstraints(authorsInput.get(i), 2, i + 7);
                i = i + 1;
            }
        });

        book = null;
        // ***SAVE BUTTON ADDBOOK FUNCTION***

        saveButtonAddBook.setOnAction(e -> {
            System.out.println("Save button pressed");
            ArrayList<Author> authorList = new ArrayList<>();

            //kolla på här
            for (int i = 0; i < authorsInput.size(); i++) {
                if (!authorsInput.get(i).getText().equals("")) {
                    Author author = new Author(authorsInput.get(i).getText());
                    authorList.add(author);
                }
            }
            try {
                book = new Book(titleInput.getText(),
                        authorList,
                        genreList.getSelectionModel().getSelectedItem().toString(),
                        ISBNInput.getText(),
                        Integer.parseInt(gradeList.getSelectionModel().getSelectedItem().toString()),
                        Integer.parseInt(editionInput.getText()),
                        Double.parseDouble(priceInput.getText()));

                System.out.println(book);
                primaryStage.close();
            } catch (NumberFormatException e1) {
                System.out.println("Wrong input");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Input, try again");
                alert.setContentText("INVALID INPUT ON PRICE OR EDITION, Please try again");
                alert.showAndWait();

            }

        });

        // label title
        GridPane.setConstraints(labelAddTitle, 1, 0);

        //label isbn
        GridPane.setConstraints(labelAddISBN, 1, 1);

        //label edition
        GridPane.setConstraints(labelAddEdition, 1, 2);

        //label price
        GridPane.setConstraints(labelAddPrice, 1, 3);

        //label genre
        GridPane.setConstraints(labelAddGenre, 1, 4);

        //label authors
        GridPane.setConstraints(labelAddAuthors, 1, 7);
        //grade 
        GridPane.setConstraints(labelGrade, 1, 6);

        //textfield input
        GridPane.setConstraints(titleInput, 2, 0);

        GridPane.setConstraints(ISBNInput, 2, 1);

        GridPane.setConstraints(editionInput, 2, 2);

        GridPane.setConstraints(priceInput, 2, 3);

        GridPane.setConstraints(authorsInput.get(0), 2, 7);

        GridPane.setConstraints(gradeList, 2, 6);

        //combo box
        GridPane.setConstraints(genreList, 2, 4);

        //+ button
        GridPane.setConstraints(anotherAuthor, 3, 7);

        //save button
        GridPane.setConstraints(saveButtonAddBook, 4, 4);

        //filler
        primaryStage.setScene(scene);
        primaryStage.showAndWait();

        return book;
    }

    public static Button backButton(Stage stage) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.close());
        GridPane.setConstraints(backButton, 4, 3);
        return backButton;

    }

}
