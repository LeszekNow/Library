import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Book {
    private int id;
    private String title;
    private String author;
    private int borrowPrice;
    private int pages;

    public Book(String title, String author, int borrowPrice, int pages) {
        this.title = title;
        this.author = author;
        this.borrowPrice = borrowPrice;
        this.pages = pages;
    }

    public Book() {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowPrice = borrowPrice;
        this.pages = pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getBorrowPrice() {
        return borrowPrice;
    }

    public void setBorrowPrice(int borrowPrice) {
        this.borrowPrice = borrowPrice;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book id: " + id + ", Title: " + title + ", Author: " + author + ", Price: " + borrowPrice + ", Pages number: " + pages + ".";
    }
    //metoda do zapisywania książek w bazie danych
    //metoda do zapisywania książek do listy

}
