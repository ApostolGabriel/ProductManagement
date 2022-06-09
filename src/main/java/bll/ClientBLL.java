package bll;

import dao.AdminDAO;
import dao.ClientDAO;
import model.Administrator;
import model.Client;

import java.util.NoSuchElementException;

public class ClientBLL {

    private ClientDAO clientDAO;

    public ClientBLL(){
        this.clientDAO = new ClientDAO();
    }

    public Client isClient(String Name, String Password){
        Client cl = clientDAO.findClient(Name,Password);
        if (cl == null) {
            throw new NoSuchElementException("Not a client");
        }
        return cl;
    }

    public void insertClient(String Name, String Password){
        Client cl = new Client(Name,Password);
        clientDAO.insert(cl);
    }

    public Client findById(int id){
        Client cl = clientDAO.findClientById(id);
        if (cl == null) {
            throw new NoSuchElementException("Not a client");
        }
        return cl;
    }

    public boolean notUser(String name){
        Client cl = clientDAO.findClientByName(name);
        return cl == null;
    }
}
