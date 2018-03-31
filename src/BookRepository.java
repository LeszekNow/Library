import java.util.ArrayList;

public class BookRepository {
    static ArrayList<Book>booksList = new ArrayList<>();


    public ArrayList<Book> getBooksList() {
        return booksList;
    }

    public void addToBooksList(Book book) {
        this.booksList.add(book);
    }

    public Book getBook(int index){
        return booksList.get(index);
    }

    public void showBooks(){
        for(Book book : booksList){
            System.out.println(book.toString());
        }
    }
}
