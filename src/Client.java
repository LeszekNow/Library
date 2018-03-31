import java.sql.*;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String password;
    private String pesel;

    public Client(String name, String surname, String password, String pesel) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.pesel = pesel;
    }

    public Client() {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.pesel = pesel;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return "Client: " + name + " " + surname +".";
    }
    //metoda do zapisywania w bazie
    //metoda do usuwania z bazy
    //metoda do usuwania z listy w repozytorium


}
