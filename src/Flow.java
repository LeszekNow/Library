import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Flow {
    public static void start() throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        do {
            Menu.displayMainMenu();
            switch (scanner.nextInt()) {
                case 1:
                    createNewClient();
                    break;
                case 2:
                    logInAndGo();
                    Menu.displaySubMenu();
                    executeSubMenuFlow();
                    break;
                case 3:
                    deleteClient();
                    break;
                case 4:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

        public static void executeSubMenuFlow (){
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()){
                case 1:
                    borrowBook();
                    Menu.displaySubMenu();
                    executeSubMenuFlow();
                    break;
                case 2:
                    giveBackBook();
                    Menu.displaySubMenu();
                    executeSubMenuFlow();
                    break;
                case 3:
                    System.out.println("--------------------");
                    for(Book book : SQLConnection.showListOfBooks()){
                        System.out.println(book.toString());
                    }
                    System.out.println("--------------------");
                    Menu.displaySubMenu();
                    executeSubMenuFlow();
                    break;
                case 4:
                    System.out.println("--------------------");
                    for(String s : SQLConnection.showListOfOrders()){
                        System.out.println(s);
                    }
                    System.out.println("--------------------");
                    Menu.displaySubMenu();
                    executeSubMenuFlow();
                    break;
                case 5:
                    System.out.println("--------------------");
                    for(Client client : SQLConnection.showListOfClients()){
                        System.out.println(client.toString());
                    }
                    System.out.println("--------------------");
                    Menu.displaySubMenu();
                    executeSubMenuFlow();
                    break;
                case 6:
                    editMyAccount();
                    Menu.displaySubMenu();
                    executeSubMenuFlow();
                    break;
                case 7:
                    createNewBook();
                    Menu.displaySubMenu();
                    executeSubMenuFlow();
                    break;
                case 8:
                    deleteBook();
                    Menu.displaySubMenu();
                    executeSubMenuFlow();
                    break;
                case 9:
                    System.exit(0);
                    break;
            }
        }
    public static void createNewClient(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------\nEnter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Enter your PESEL: ");
        String pesel = scanner.nextLine();
        Client client = new Client(name, surname, password, pesel);
        String sqlInsertClient = "INSERT INTO tclient (name, surname, password, pesel) " +
                "VALUES("+"'"+ name + "', '" + surname + "', '" + password + "', '" + pesel + "' );";
       SQLConnection.connectToDataBase(sqlInsertClient);
        System.out.println("--------------------\nNew account added successfully!\n--------------------");
    }

    public static void createNewBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------\nEnter book title: ");
        String title = scanner.nextLine();
        System.out.println("Enter author name and surname: ");
        String author = scanner.nextLine();
        System.out.println("Enter book's rent-price: ");
        int borrowprice = scanner.nextInt();
        System.out.println("Enter number of pages ");
        int pages = scanner.nextInt();
        Book book = new Book(title, author, borrowprice, pages);
        String sqlInsertBook = "INSERT INTO tbook (title, author, borrowprice, pages) " +
                "VALUES("+"'"+ title + "', '" + author + "', " + borrowprice + ", " + pages + " );";
        SQLConnection.connectToDataBase(sqlInsertBook);
        System.out.println("--------------------\nAdding in progress..." +
                "After LogOut and LogIn data will be actualised!\n--------------------");
    }

    public static void deleteClient(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------\nEnter your PESEL: ");
        String peselOfDeletingClient = scanner.nextLine();
        String sqlDeleteClient = "DELETE FROM tclient WHERE pesel = " + "'" + peselOfDeletingClient + "';";
        SQLConnection.connectToDataBase(sqlDeleteClient);
        System.out.println("--------------------\nAccount deleted successfully!\n--------------------");
    }

    public static void logInAndGo(){
        Scanner scanner = new Scanner(System.in);
        String enteredPassword = "";
        String enteredPesel = "";
        String sqlPasswordToCheck = "";
        do{
            System.out.println("-------------------\nEnter your PESEL: ");
            enteredPesel = scanner.nextLine();
            System.out.println("Enter your Password: ");
            enteredPassword = scanner.nextLine();
            sqlPasswordToCheck = "SELECT password FROM tclient WHERE pesel = " + "'" + enteredPesel + "';";
        }while(!enteredPassword.equals(SQLConnection.getStringFromDataBase(sqlPasswordToCheck,"password")));
    }

    public static void borrowBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------\nSelect ID of book you want to borrow:\n--------------------");
        for(Book book : SQLConnection.showListOfBooks()){
            System.out.println(book.toString());
        }
        System.out.println("--------------------");
        int bookId = scanner.nextInt();
        System.out.println("Enter your PESEL:");
        String peselOfClient = scanner.next();
        System.out.println("Enter rental-time in days: ");
        int borrowduration = scanner.nextInt();
        String sqlGetId = "SELECT id FROM tclient WHERE pesel = '" + peselOfClient +"';";
        int clientId = SQLConnection.getNumberFromDataBase(sqlGetId, "id");
        Order order = new Order(clientId, bookId, borrowduration);
        String sqlInsertOrder ="INSERT INTO torder (clientid, bookid, borrowduration) " +
                "VALUES ("+ clientId + ", " + bookId + ", " + borrowduration + ");";
        SQLConnection.connectToDataBase(sqlInsertOrder);
        String sqlGetOrderId = "SELECT id FROM torder WHERE clientid = "
                + SQLConnection.getNumberFromDataBase(sqlGetId, "id") + ";";
        SQLConnection.getNumberFromDataBase(sqlGetOrderId, "id");
        System.out.println("-------------------\nYou've just borrowed a book!" +
                "\nYour rental id is: " + SQLConnection.getNumberFromDataBase(sqlGetOrderId, "id") +
                "\n-------------------");
    }

    public static void giveBackBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------\nEnter your rental id:");
        int rentalId = scanner.nextInt();
        String sqlDeleteOrder ="DELETE FROM torder WHERE id = "+ rentalId + ";";
        SQLConnection.connectToDataBase(sqlDeleteOrder);
        System.out.println("-------------------\nYour account is clear now\n-------------------");
    }

    public static void editMyAccount(){
        Scanner scanner = new Scanner(System.in);
        Menu.displayEditMenu();
        switch (scanner.nextInt()){
            case 1:
                changeSurname();
                break;
            case 2:
                changePassword();
                break;
        }
    }

    public static void changePassword(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------\nEnter your PESEL:");
        String clientPeselForPassword = scanner.nextLine();
       // scanner.nextLine();
        System.out.println("Enter your new password");
        String newPassword = scanner.nextLine();
        String sqlEditPassword = "UPDATE tclient SET password = '" + newPassword +
                "' WHERE pesel = '" + clientPeselForPassword + "';";
        SQLConnection.connectToDataBase(sqlEditPassword);
        System.out.println("--------------------\nAccount updated successfully\n--------------------");
    }

    public static void changeSurname(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------\nEnter your PESEL:");
        String clientPeselForSurname = scanner.nextLine();
       // scanner.nextLine();
        System.out.println("Enter your new surname");
        String newSurname = scanner.nextLine();
        String sqlEditSurname = "UPDATE tclient SET surname = '" + newSurname +
                "' WHERE pesel = '" + clientPeselForSurname + "';";
        SQLConnection.connectToDataBase(sqlEditSurname);
        System.out.println("--------------------\nAccount updated successfully\n--------------------");
    }

    public static void deleteBook(){
        Scanner scanner = new Scanner(System.in);
        for(Book book : SQLConnection.showListOfBooks()){
        System.out.println(book.toString());
        }
        System.out.println("-------------------\nEnter id of book you want to delete\n-------------------");
        int bookForDelete = scanner.nextInt();
        String sqlDeleteBook = "DELETE FROM tbook WHERE id = " + bookForDelete + ";";
        SQLConnection.connectToDataBase(sqlDeleteBook);
        System.out.println("-------------------\nDeleting in progress..." +
                "After LogOut and LogIn data will be actualised!\n-------------------");
    }
}
