import java.sql.*;
import java.util.ArrayList;

public class SQLConnection {

    public static void connectToDataBase(String sqlAccess){
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/library?"
                            + "user=root&password=");
            preparedStatement = connect.prepareStatement(sqlAccess);
            preparedStatement.executeUpdate();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Client> showListOfClients(){
        Connection connect = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/library?"
                            + "user=root&password=");
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tclient;");
            while(resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setSurname(resultSet.getString("surname"));
                client.setPassword(resultSet.getString("password"));
                client.setPesel(resultSet.getString("pesel"));
                ClientRepository.clientList.add(client);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ClientRepository.clientList;
    }

    public static ArrayList<String> showListOfOrders(){
        ArrayList<String>stringList = new ArrayList<>();
        Connection connect = null;
        ResultSet resultSet = null;
        Statement statement = null;
        int bookid = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/library?"
                            + "user=root&password=");
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT bookid FROM torder;");
            while(resultSet.next()) {
                bookid = resultSet.getInt("bookid");
                stringList.add(SQLConnection.getStringFromDataBase("SELECT title FROM tbook " +
                        "WHERE id = " + bookid + ";","title"));
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return stringList;
    }

    public static ArrayList<Book> showListOfBooks (){
        Connection connect = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/library?"
                            + "user=root&password=");
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tbook;");
            while(resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setBorrowPrice(resultSet.getInt("borrowprice"));
                book.setPages(resultSet.getInt("pages"));
                BookRepository.booksList.add(book);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return BookRepository.booksList;
    }

    public static int getNumberFromDataBase(String sqlAccess, String columnLabel) {
        Connection connect = null;
        ResultSet resultSet = null;
        Statement statement = null;
        int number = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/library?"
                            + "user=root&password=");
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sqlAccess);
            while (resultSet.next()) {
                number = resultSet.getInt(""+ columnLabel +"");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }

    public static String getStringFromDataBase(String sqlAccess, String columnLabel){
        Connection connect = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String string = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/library?"
                            + "user=root&password=");
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sqlAccess);
            while(resultSet.next()) {
                string = resultSet.getString(""+ columnLabel +"");
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return string;
    }
}
