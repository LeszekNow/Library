import java.util.Scanner;

public class Menu {
    public static void displayMainMenu(){
        System.out.println("-------------------\nWelcome in my Library-Task\n-------------------\nChoose an option:");
        System.out.println("1.Create account\n2.LogIn\n3.Delete an account\n4.Exit\n-------------------");
    }

    public static void displaySubMenu(){
        System.out.println("-------------------\nChoose what you want to do:\n------------------- ");
        System.out.println("1.Borrow a book\n2.Give back a book\n3.Show me list of books\n4.Show me list of rentals\n5.Show me list of clients\n6.Edit my account\n7.Add new book\n8.Delete book\n9.LogOut");

    }

    public static void displayEditMenu(){
        System.out.println("-------------------\nChoose option you want to edit:\n1.Surname\n2.Password\n-------------------");
    }

}
