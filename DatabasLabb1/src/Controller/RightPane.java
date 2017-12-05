/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CollectionOfBooks;
import Model.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.sql.rowset.spi.SyncProvider;

/**
 *
 * @author Ahmed Heidari 2
 */
public class RightPane {

    private CollectionOfBooks COBooks;
    private TableView<Book> table;
    private SearchBooks books = new SearchBooks();

    public RightPane() {
    }

    public Pane initRightPane(TableView<Book> table, CollectionOfBooks COBooks, User user, Connection con) {
        this.COBooks = COBooks;
        this.table = table;
        VBox deletePane = new VBox(20);
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update");
        Button getBooksButton = new Button("Get books");

        deletePane.setMaxWidth(100);

        deletePane.setTranslateX(-80);
        deletePane.setTranslateY(150);

        deleteButton.setLayoutX(-10);
        deleteButton.setLayoutY(110);

        updateButton.setLayoutX(-10);
        updateButton.setLayoutY(140);

        getBooksButton.setLayoutX(-10);
        getBooksButton.setLayoutY(170);

        deleteButton.setOnAction(e -> {
            deleteBook();
            table.setItems(books.getBooks(COBooks));
        });

        updateButton.setOnAction(e -> {
            user.updateBook(table, COBooks);
        });

        getBooksButton.setOnAction(e -> {         
            user.getBook(table, COBooks, con);
            table.setItems(books.getBooks(COBooks));
        });

        deletePane.getChildren().addAll(deleteButton, updateButton, getBooksButton);
        return deletePane;
    }

    public void deleteBook() {
        COBooks.deleteBookByTitle(table.getSelectionModel().getSelectedItem());
    }

    public void getBooks(Connection con) throws SQLException {

        Statement statement = con.createStatement();
        ResultSet rsBook = statement.executeQuery("SELECT * FROM library.t_book join library.t_authorbooks join library.t_author "
                + "WHERE t_book.isbn = t_authorbooks.ISBN AND t_authorbooks.Author_ID = t_author.Author_ID");
        ArrayList<Author> authorList = new ArrayList<>();
        while (rsBook.next()) {
            String Title = rsBook.getString("Title");
            String isbn = rsBook.getString("isbn");
            String genre = rsBook.getString("Genre");
            Date pubYear = rsBook.getDate("pubYear");
            int grade = rsBook.getInt("grade");
            COBooks.addBook(new Book(Title, authorList, genre, isbn,grade,3, 150));

        }

        table.setItems(books.getBooks(COBooks));

    }

}
