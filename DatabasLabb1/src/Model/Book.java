/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Ahmed Heidari 2
 */

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author Ahmed Heidari 2
 * Book implements comparable to compare the books and sort them by name
 * it implements serializable so you can write and read from file
 */
public class Book implements Comparable<Book>,Serializable {


    private String title;
    private ArrayList<Author> authors = new ArrayList<>();
    private String genre;
    private String isbn;
    private int edition;
    private int grade;
    private double price;
    
    

    /**
     * 
     * @param title
     * @param a
     * @param edition
     * @param price
     * @param genre
     * @param isbn 
     */
    public Book(String title, ArrayList<Author> a, String genre, String isbn, int grade, int edition, double price) {
        this.title = title;
        this.authors = a;
        this.genre = genre;
        this.isbn = isbn;
        this.grade=grade;
        this.edition = edition;
        this.price = price;
    }
    
    /**
     * compareTo is a interface that is implementet. This method is used
     * to sort the books by the title. If the title are the same then it goes by
     * the first author in the alfabeth.
     * @param o
     * @return 
     */

    @Override
    public int compareTo(Book o) {
        if (!title.equals(o.getTitle())) {
            return title.compareTo(o.getTitle());
        } else {
            return authors.get(0).getName().compareTo(o.getAuthors().get(0).getName());
        }
    }


    public String getTitle() {
        return title;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }
    
  

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", authors=" + authors + ", genre=" + genre + ", isbn=" + isbn + ", edition=" + edition + ", price=" + price + '}';
    }

}

