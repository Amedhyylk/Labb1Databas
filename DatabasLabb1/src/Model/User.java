/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.AddBook;
import Controller.RightPane;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author John
 */
public class User {

    private int persID;
    private String firstName;
    private String lastName;
    private Date dob;
    private String city;
    private int zipCode;
    private String street;
    private String email;
    private String userName;
    private SearchBooks books = new SearchBooks();
    private CollectionOfBooks COBooks;

    public User() {
        this.persID = 9999;
        this.firstName = "Guest";
        this.lastName = "";
        this.dob = new Date(Calendar.getInstance().getTimeInMillis());
        this.city = "";
        this.zipCode = 0;
        this.street = "";
        this.email = "";
        this.userName = "Guest";
    }

    public int getPersID() {
        return persID;
    }

    public void setPersID(int persID) {
        this.persID = persID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void insertBook(Book b, Date pubYear) {
        //isbn, title, genre, pubyear
        b.getIsbn();
        b.getTitle();
        b.getGenre();
        //send in pubyear här

    }

    public void updateBook(TableView<Book> table, CollectionOfBooks COBooks) {
        //KOLLA SKYPE för uppdatering
        //UPDATE `library`.`t_book` SET `Title`='Sagan om Ringen3' WHERE `isbn`='2374';

        table.getSelectionModel().getSelectedItem().getIsbn();

    }

    public void getBook(TableView<Book> table, CollectionOfBooks COBooks, Connection con) {
        this.COBooks = COBooks;
        try {
            Statement statement = con.createStatement();
            ResultSet rsBook = statement.executeQuery("SELECT * FROM library.t_book join library.t_authorbooks join library.t_author "
                    + "WHERE t_book.isbn = t_authorbooks.ISBN AND t_authorbooks.Author_ID = t_author.Author_ID");
            ArrayList<Author> authorList = new ArrayList<>();
            for (int i = 0; i < COBooks.getBooks().size(); i++) {
                if (!COBooks.getBooks().isEmpty()) {
                    COBooks.deleteBookByTitle(COBooks.getBooks().get(i));
                    i--;
                }
            }
            while (rsBook.next()) {
                String Title = rsBook.getString("Title");
                String isbn = rsBook.getString("isbn");
                String genre = rsBook.getString("Genre");
                Date pubYear = rsBook.getDate("pubYear");
                int grade = rsBook.getInt("grade");
                COBooks.addBook(new Book(Title, authorList, genre, isbn, grade, 3, 150));
            }
            table.setItems(books.getBooks(COBooks));
        } catch (SQLException ex) {
            Logger.getLogger(RightPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAuthor(Book b) {
        //gör en sql syntax som frågar om author listan
        //KOLLA SKYPE FÖR SEARCH
    }

    public void searchTitle(Connection con, TextField searchInput, TableView table, CollectionOfBooks COBooks, SearchBooks books) throws SQLException {
        this.COBooks = COBooks;
        try {
            Statement statement = con.createStatement();
            ResultSet rsBook = statement.executeQuery("SELECT * FROM library.t_book WHERE Title LIKE '%" + searchInput.getText() + "%'");
            ArrayList<Author> authorList = new ArrayList<>();
            clearTable();
            while (rsBook.next()) {
                String Title = rsBook.getString("Title");
                String isbn = rsBook.getString("isbn");
                String genre = rsBook.getString("Genre");
                Date pubYear = rsBook.getDate("pubYear");
                int grade = rsBook.getInt("grade");
                COBooks.addBook(new Book(Title, authorList, genre, isbn, grade, 3, 150));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RightPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(books.getBooks(COBooks));
    }

    public void searchAuthor(Connection con, TextField searchInput, TableView table, CollectionOfBooks COBooks, SearchBooks books) throws SQLException {
        this.COBooks = COBooks;
        try {
            Statement statement = con.createStatement();
            ResultSet rsBook = statement.executeQuery("SELECT * FROM library.t_book natural join library.t_authorbooks WHERE Author_ID = (SELECT library.t_author.Author_ID FROM library.t_author WHERE library.t_author.Name LIKE '%" + searchInput.getText() + "%')");

            ArrayList<Author> authorList = new ArrayList<>();
            clearTable();
            while (rsBook.next()) {
                String Title = rsBook.getString("Title");
                String isbn = rsBook.getString("isbn");
                String genre = rsBook.getString("Genre");
                Date pubYear = rsBook.getDate("pubYear");
                int grade = rsBook.getInt("grade");
                COBooks.addBook(new Book(Title, authorList, genre, isbn, grade, 3, 150));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RightPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(books.getBooks(COBooks));
    }

    public void searchISBN(Connection con, TextField searchInput, TableView table, CollectionOfBooks COBooks, SearchBooks books) throws SQLException {
        this.COBooks = COBooks;
        try {
            Statement statement = con.createStatement();
            ResultSet rsBook = statement.executeQuery("SELECT * FROM library.t_book  WHERE isbn LIKE '%" + searchInput.getText() + "%'");
            ArrayList<Author> authorList = new ArrayList<>();
            clearTable();
            while (rsBook.next()) {
                String Title = rsBook.getString("Title");
                String isbn = rsBook.getString("isbn");
                String genre = rsBook.getString("Genre");
                Date pubYear = rsBook.getDate("pubYear");
                int grade = rsBook.getInt("grade");
                COBooks.addBook(new Book(Title, authorList, genre, isbn, grade, 3, 150));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RightPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(books.getBooks(COBooks));
    }

    public void searchGrade(Connection con, TextField searchInput, TableView table, CollectionOfBooks COBooks, SearchBooks books) throws SQLException {
        this.COBooks = COBooks;
        //KOM IHÅG ERROR PÅ INPUT OM DEN ÄR NULL
        try {
            Statement statement = con.createStatement();
            ResultSet rsBook = statement.executeQuery("SELECT * FROM library.t_book  WHERE grade = " + Integer.parseInt(searchInput.getText()));
            ArrayList<Author> authorList = new ArrayList<>();
            clearTable();
            while (rsBook.next()) {
                String Title = rsBook.getString("Title");
                String isbn = rsBook.getString("isbn");
                String genre = rsBook.getString("Genre");
                Date pubYear = rsBook.getDate("pubYear");
                int grade = rsBook.getInt("grade");
                COBooks.addBook(new Book(Title, authorList, genre, isbn, grade, 3, 150));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RightPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(books.getBooks(COBooks));
    }

    public void searchGenre(Connection con, TextField searchInput, TableView table, CollectionOfBooks COBooks, SearchBooks books) throws SQLException {
        this.COBooks = COBooks;
        try {
            Statement statement = con.createStatement();
            ResultSet rsBook = statement.executeQuery("SELECT * FROM library.t_book WHERE Genre LIKE '%" + searchInput.getText() + "%'");
            ArrayList<Author> authorList = new ArrayList<>();
            clearTable();
            while (rsBook.next()) {
                String Title = rsBook.getString("Title");
                String isbn = rsBook.getString("isbn");
                String genre = rsBook.getString("Genre");
                Date pubYear = rsBook.getDate("pubYear");
                int grade = rsBook.getInt("grade");
                COBooks.addBook(new Book(Title, authorList, genre, isbn, grade, 3, 150));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RightPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(books.getBooks(COBooks));
    }

    public void clearTable() {
        for (int i = 0; i < COBooks.getBooks().size(); i++) {
            if (!COBooks.getBooks().isEmpty()) {
                COBooks.deleteBookByTitle(COBooks.getBooks().get(i));
                i--;
            }
        }
    }

    @Override
    public String toString() {
        return "User{" + "persID=" + persID + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", city=" + city + ", zipCode=" + zipCode + ", street=" + street + ", email=" + email + ", userName=" + userName + '}';
    }

}
