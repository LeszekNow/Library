public class Order {
    private int id;
    private int clientId;
    private int bookId;
    private int borrowDuration;

    public Order(int borrowDuration, int clientId, int bookId) {
        this.borrowDuration = borrowDuration;
        this.clientId = clientId;
        this.bookId = bookId;
    }

    public Order() {
        this.id = id;
        this.clientId = clientId;
        this.bookId = bookId;
        this.borrowDuration = borrowDuration;
    }

    public int getBorrowDuration() {
        return borrowDuration;
    }

    public int getClientId() {
        return clientId;
    }

    public int getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "Order: " + clientId + ":" + bookId + "\n Duration: " + borrowDuration + " days.";
    }
}
