/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ahmed Heidari 2
 */
public class SearchBooks {
    
    /**
     *
     */
    public SearchBooks()
    {
        
    }

    /**
     * Search books by title, returns the books so we can update the table.
     * @param s
     * @param COBooks
     * @return
     */
    public ObservableList<Book> getBooksTitleSearch(String s,CollectionOfBooks COBooks) {
        ObservableList<Book> books = FXCollections.observableArrayList();

        if (COBooks.getBooks().size() > 0) {
            for (int i = 0; i < COBooks.getBooksByTitle(s).size(); i++) {
                books.add(COBooks.getBooksByTitle(s).get(i));
            }
        }

        return books;
    }

    /**
     * Search books by author, returns the books so we can update the table.
     * @param s
     * @param COBooks
     * @return
     */
    public ObservableList<Book> getBooksAuthorSearch(String s,CollectionOfBooks COBooks) {
        ObservableList<Book> books = FXCollections.observableArrayList();

        if (COBooks.getBooks().size() > 0) {
            for (int i = 0; i < COBooks.getByAuthor(new Author(s)).size(); i++) {
                books.add(COBooks.getByAuthor(new Author(s)).get(i));
            }
        }

        return books;
    }

    /**
     * Search books by ISBN, returns the books so we can update the table.
     * @param s
     * @param COBooks
     * @return
     */
    public ObservableList<Book> getBooksISBNSearch(String s,CollectionOfBooks COBooks) {
        ObservableList<Book> books = FXCollections.observableArrayList();

        if (COBooks.getBooks().size() > 0) {
            for (int i = 0; i < COBooks.getByIsbn(s).size(); i++) {
                books.add(COBooks.getByIsbn(s).get(i));
            }
        }

        return books;
    }
    
    /**
     * Updates the table
     * @param COBooks
     * @return
     */
    public ObservableList<Book> getBooks(CollectionOfBooks COBooks) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        if (COBooks.getBooks().size() > 0) {
            for (int i = 0; i < COBooks.getBooks().size(); i++) {
                books.add(COBooks.getBooks().get(i));
            }
        }
        return books;
    }
    
}
