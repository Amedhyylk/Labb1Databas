/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javax.xml.bind.ParseConversionEvent;

/**
 *
 * @author Ahmed Heidari 2
 */
public class LowerPane {

    private User user;

    public LowerPane() {

    }

    public Pane initLowerPane(TableView<Book> table, CollectionOfBooks COBooks, User user, Connection con) {

        this.user = user;
        SearchBooks books = new SearchBooks();

        Button searchButton = new Button("Search");

        TextField searchInput = new TextField();
        searchInput.setPromptText("Search here ...");

        HBox searchOptionsMenu = new HBox(20);
        searchOptionsMenu.setMinWidth(600);
        searchOptionsMenu.setPadding(new Insets(20));
        searchOptionsMenu.setSpacing(20);
        searchOptionsMenu.setMinHeight(40);
        ToggleGroup searchToggle = new ToggleGroup();

        RadioButton searchTitle = new RadioButton("Title");
        searchTitle.setTranslateY(4);
        searchTitle.setSelected(true);

        RadioButton searchAuthor = new RadioButton("Author");
        searchAuthor.setTranslateY(4);

        RadioButton searchISBN = new RadioButton("ISBN");
        searchISBN.setTranslateY(4);

        RadioButton searchGrade = new RadioButton("Grade");
        searchGrade.setTranslateY(4);

        RadioButton searchGenre = new RadioButton("Genre");
        searchGenre.setTranslateY(4);

        searchTitle.setToggleGroup(searchToggle);
        searchAuthor.setToggleGroup(searchToggle);
        searchISBN.setToggleGroup(searchToggle);
        searchGrade.setToggleGroup(searchToggle);
        searchGenre.setToggleGroup(searchToggle);

        searchButton.setOnAction(e -> {
            if (searchToggle.getSelectedToggle() == searchTitle) {
                try {
                    user.searchTitle(con, searchInput, table, COBooks, books);
                } catch (SQLException ex) {
                    Logger.getLogger(LowerPane.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (searchToggle.getSelectedToggle() == searchAuthor) {
                try {
                    user.searchAuthor(con, searchInput, table, COBooks, books);
                } catch (SQLException ex) {
                    Logger.getLogger(LowerPane.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (searchToggle.getSelectedToggle() == searchISBN) {
                try {
                    user.searchISBN(con, searchInput, table, COBooks, books);
                } catch (SQLException ex) {
                    Logger.getLogger(LowerPane.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (searchToggle.getSelectedToggle() == searchGrade) {
                try {
                    user.searchGrade(con, searchInput, table, COBooks, books);
                } catch (SQLException ex) {
                    Logger.getLogger(LowerPane.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (searchToggle.getSelectedToggle() == searchGenre) {
                try {
                    user.searchGenre(con, searchInput, table, COBooks, books);
                } catch (SQLException ex) {
                    Logger.getLogger(LowerPane.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        searchOptionsMenu.getChildren().addAll(searchTitle, searchAuthor, searchISBN, searchGrade, searchGenre, searchInput, searchButton);

        return searchOptionsMenu;

    }

    public void searchISBN(Connection con, TextField searchInput, TableView table, CollectionOfBooks COBooks, SearchBooks books) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet rsBook = statement.executeQuery("SELECT * FROM library.t_book WHERE isbn LIKE '%" + searchInput.getText() + "%'");
        System.out.println("SELECT * FROM library.t_book WHERE isbn LIKE '%" + searchInput.getText() + "%'");
        ArrayList<Author> authorList = new ArrayList<>();
        while (rsBook.next()) {
            String Title = rsBook.getString("Title");
            String isbn = rsBook.getString("isbn");
            String genre = rsBook.getString("Genre");
            Date pubYear = rsBook.getDate("pubYear");
            int grade = rsBook.getInt("grade");

            COBooks.addBook(new Book(Title, authorList, genre, isbn, grade, 3, 150));
        }

        table.setItems(books.getBooks(COBooks));

    }

    public void searchGenre(Connection con, TextField searchInput, TableView table, CollectionOfBooks COBooks, SearchBooks books) throws SQLException {

        Statement statement = con.createStatement();
        ResultSet rsBook = statement.executeQuery("SELECT * FROM library.t_book WHERE Genre LIKE '%" + searchInput.getText() + "%'");
        ArrayList<Author> authorList = new ArrayList<>();
        while (rsBook.next()) {
            String Title = rsBook.getString("Title");
            String isbn = rsBook.getString("isbn");
            String genre = rsBook.getString("Genre");
            Date pubYear = rsBook.getDate("pubYear");
            int grade = rsBook.getInt("grade");

            COBooks.addBook(new Book(Title, authorList, genre, isbn, grade, 3, 150));
        }

        table.setItems(books.getBooks(COBooks));

    }

    public void searchGrade(Connection con, TextField searchInput, TableView table, CollectionOfBooks COBooks, SearchBooks books) throws SQLException {

        Statement statement = con.createStatement();
        ResultSet rsBook = statement.executeQuery("SELECT * FROM library.t_book  WHERE grade = " + Integer.parseInt(searchInput.getText()));
        ArrayList<Author> authorList = new ArrayList<>();
        while (rsBook.next()) {
            String Title = rsBook.getString("Title");
            String isbn = rsBook.getString("isbn");
            String genre = rsBook.getString("Genre");
            int grade = rsBook.getInt("grade");
            Date pubYear = rsBook.getDate("pubYear");

            COBooks.addBook(new Book(Title, authorList, genre, isbn, grade, 3, 150));
        }

        table.setItems(books.getBooks(COBooks));

    }

}
