/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Book;
import Model.*;
import Model.CollectionOfBooks;
import View.GUIMenu;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed Heidari 2
 */
public class TopPane {

    private File fileSelected = null;
    private SearchBooks books = new SearchBooks();
    private String[] connection;

    public TopPane() {

    }

    public MenuBar initTopPane(Stage primaryStage, TableView<Book> table, CollectionOfBooks COBooks) {

        Menu fileMenu = new Menu("File");
        Menu menuSearch = new Menu("Search Book");
        Menu menuShowList = new Menu("Show List");
        Menu menuAddBook = new Menu("Manage");
        Menu connectMenu = new Menu("Connect...");

        MenuItem addBookItem = new MenuItem("Add book");
        MenuItem deleteBookItem = new MenuItem("Delete book");
        MenuItem exitMenuItem = new MenuItem("Exit");
        MenuItem saveFileItem = new MenuItem("Save File");
        MenuItem saveFileAsItem = new MenuItem("Save File As...");
        MenuItem showListItem = new MenuItem("Show all books");
        MenuItem titleItem = new MenuItem("By title");
        MenuItem authorItem = new MenuItem("By Author");
        MenuItem ISBNItem = new MenuItem("By ISBN");
        MenuItem ConnectItem = new MenuItem("To Database");
        saveFileItem.setDisable(true);
        MenuItem loadFile = new MenuItem("Load File...");
        MenuItem updateItem = new MenuItem("Update book");

        addBookItem.setOnAction(e -> {
            Book b = AddBook.display();
            if (b != null) {
                COBooks.addBook(b);
            }
            table.setItems(books.getBooks(COBooks));

        });

        ConnectItem.setOnAction(e -> {
            connection = TopPaneConnect.display();
            System.out.println(connection[0] + " " + connection[1]);
            try {
                // fixa IP sen.
                Connect connect = new Connect("5", "5");
            } catch (SQLException ex) {
                Logger.getLogger(TopPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        showListItem.setOnAction(e -> {
            table.setItems(books.getBooks(COBooks));
        });

        deleteBookItem.setOnAction(e -> {
            deleteBook(table, COBooks);
            table.setItems(books.getBooks(COBooks));
        });

        exitMenuItem.setOnAction(e -> {
            primaryStage.close();
        });

        titleItem.setOnAction(e -> {
            table.setItems(books.getBooksTitleSearch(TopPaneSearchBook.display(), COBooks));

        });

        authorItem.setOnAction(e -> {
            table.setItems(books.getBooksAuthorSearch(TopPaneSearchBook.display(), COBooks));

        });

        ISBNItem.setOnAction(e -> {
            table.setItems(books.getBooksISBNSearch(TopPaneSearchBook.display(), COBooks));

        });

        saveFileItem.setOnAction(e -> {
            try {
                COBooks.saveFile(fileSelected.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(GUIMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        saveFileAsItem.setOnAction(e -> {

            FileChooser fileChoice = new FileChooser();
            fileChoice.setInitialDirectory(new File(System.getProperty("user.home")));
            fileSelected = fileChoice.showSaveDialog(null);

            if (fileSelected != null) {
                try {
                    COBooks.saveFile(fileSelected.getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(GUIMenu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    throw new IllegalArgumentException("Book cannot be null");
                }
            }
            try {
                COBooks.saveFile(fileSelected.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(GUIMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {

            }

        });

        loadFile.setOnAction(e -> {
            FileChooser fileChoice = new FileChooser();

            fileChoice.setInitialDirectory(new File(System.getProperty("user.home")));

            fileSelected = fileChoice.showOpenDialog(null);

            if (fileSelected != null) {
                try {
                    COBooks.readFile(fileSelected.getAbsolutePath());
                    saveFileItem.setDisable(false);
                } catch (IOException ex) {
                    Logger.getLogger(GUIMenu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GUIMenu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    throw new IllegalArgumentException("Book cannot be null");
                }
            }

            table.setItems(books.getBooks(COBooks));
        });

        fileMenu.getItems().addAll(loadFile, saveFileItem, saveFileAsItem, new SeparatorMenuItem(), exitMenuItem);

        menuSearch.getItems().addAll(titleItem, authorItem, ISBNItem);
        menuAddBook.getItems().addAll(addBookItem, deleteBookItem, updateItem);
        menuShowList.getItems().addAll(showListItem);
        connectMenu.getItems().addAll(ConnectItem);

        MenuBar menuBar = new MenuBar();

        menuBar.getMenus().addAll(fileMenu, menuSearch, menuShowList, menuAddBook, connectMenu);

        return menuBar;

    }

    public void deleteBook(TableView<Book> table, CollectionOfBooks COBooks) {
        COBooks.deleteBookByTitle(table.getSelectionModel().getSelectedItem());
    }


}
