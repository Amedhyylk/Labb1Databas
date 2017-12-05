/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;




import Controller.AddBook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ahmed Heidari 2
 */
public class CollectionOfBooks implements Serializable {

    private ArrayList<Book> books = new ArrayList();

    
    public CollectionOfBooks()
    {
        
    }

    /**
     *
     * addBook is a method that adds a book to the list.
     * It throw´s a IllegalArgumentException if books is null.
     * @param book
     */
    
    
    public void addBook(Book book) {
        if(book==null)
        {
            throw new IllegalArgumentException("Book cannot be null");
        }
        
        books.add(book);
    }

    /**
     *
     * return  a list of books matching the title, is it not case sensitve.
     * @param title
     * @return
     */
    public ArrayList<Book> getBooksByTitle(String title) {
        ArrayList<Book> searchBooks = new ArrayList<>();


        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().toUpperCase().contains(title.toUpperCase())) {
                searchBooks.add(books.get(i));
            }
           
        }
        return searchBooks;
    }

    /**
     * returns a list of books matching the ISBN.
     * @param isbn
     * @return
     */
    public ArrayList<Book> getByIsbn(String isbn) {
        ArrayList<Book> searchIsbn = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().contains(isbn)) {
                searchIsbn.add(books.get(i));
            }

        }
        return searchIsbn;
    }

    /**
     * returns a list of book matching the authors, it is not case sensitive
     * @param author
     * @return
     */
    public ArrayList<Book> getByAuthor(Author author) {

        ArrayList<Book> searchAuthor = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            for (int j = 0; j < books.get(i).getAuthors().size(); j++) {
                if (books.get(i).getAuthors().get(j).getName().toUpperCase().contains(author.getName().toUpperCase())) {
                    searchAuthor.add(books.get(i));
                }
            }
        }
        return searchAuthor;

    }

   

    /**
     *deletes a book matching the input
     * @param b
     * @return
     */
    public boolean deleteBookByTitle(Book b) {
        return books.remove(b);
    }
    
    public Book updateBook(Book b) {
        return AddBook.display();
    }

    /**
     *read file method reads and saves the file in books
     * 
     * @param s
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readFile(String s) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        try {
        
            FileInputStream fin = new FileInputStream(s);
          
           
            ois = new ObjectInputStream(fin);
            
         
            books = (ArrayList<Book>) ois.readObject();
        } finally {
    
            if (ois != null) {
                ois.close();
            }
        
        }
    }

    /**
     *saves the books into the file
     * @param s
     * @throws IOException
     */
    public void saveFile(String s) throws IOException {
        ObjectOutputStream oos = null;
        
        try {
            FileOutputStream fout = new FileOutputStream(s);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(books); 
        
        } finally {
            oos.close();
        }
    }


    /**
     *Sorts the book by title
     */
    public void sortBooksTitle() {
        Collections.sort(books);
    }

    /**
     *
     * @return
     */
    public ArrayList<Book> getBooks() {
        return (ArrayList<Book>) books.clone();
    }

    @Override
    public String toString() {
        return "CollectionOfBooks{" + "books=" + books + '}';
    }
}