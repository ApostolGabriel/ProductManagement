package model;

public class Client {

    private int idClient;
    private String Name;
    private String Password;

    Client(){ }

    public Client(String Name,String Pass){
        this.Name = Name;
        this.Password = Pass;
    }

    public Client(int idClient, String name, String password) {
        this.idClient = idClient;
        Name = name;
        Password = password;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", Name='" + Name + '\'' +
                "}";
    }
}
