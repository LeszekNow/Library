import java.util.ArrayList;

public class ClientRepository {
    static ArrayList<Client>clientList = new ArrayList<>();


    public ArrayList<Client> getClientList() {
        return clientList;

    }
    public void showClients(){
        for(Client client : clientList){
            System.out.println(client.toString());
        }
    }
}
